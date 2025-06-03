package umc8th.spring8th.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.apiPayload.exception.handler.MemberHandler;
import umc8th.spring8th.config.properties.Constants;
import umc8th.spring8th.config.properties.JwtProperties;
import umc8th.spring8th.converter.MemberConverter;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.RefreshToken;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.RefreshTokenRepository.RefreshTokenRepository;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    // 인증 정보를 받아서, JWT Access Token을 생성하고 반환
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();

        return Jwts.builder()
                // 페이로드의 sub 클레임 설정
                // 토큰의 주체를 나타내며 일반적으로 사용자 식별자(이메일, 아이디) 저장
                // 사용자를 고유하게 식별할 수 있는 값
                .setSubject(email)
                // 사용자 정의(custom) 클레임 추가
                // "role"은 클레임 이름
                // 사용자의 권한 (ex. ROLE_USER)를 가져옴
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                // 토큰이 발행된 시간 설정 (현재 시간)
                .setIssuedAt(new Date())
                // 토큰의 만료 시간을 지정
                // 현재 시간에 만료 시간을 더함
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
                // 서명을 생성
                // 비밀키를 가져와 알고리즘을 사용해 서명
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                // 설정된 내용을 바탕으로 최종 JWT 문자열을 생성
                .compact();
    }

    // Refresh Token 생성하고 반환
    public String generateRefreshToken(Authentication authentication) {
        String email = authentication.getName();

        return Jwts.builder()
                .setSubject(email)
                .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getRefresh()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 해당 JWT 토큰이 유효한지 검증
    // 파싱이 된다면 유효한 토큰
    // 토큰이 만료되었거나 혹은 위조, 형식 오류가 생기면 예외가 발생하여 false 반환
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰에서 인증 정보를 추출해서, Spring Security의 Authentication 객체로 변환
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        String email = claims.getSubject();
        String role = claims.get("role", String.class);

        User principal = new User(email, "", Collections.singleton(() -> role));
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }

    // HttpServletRequest 에서 토큰 값을 추출
    // getAuthentication 메소드를 이용해서 Spring Security의 Authentication 객체로 변환해줌
    public Authentication extractAuthentication(HttpServletRequest request) {
        String accessToken = resolveToken(request);
        if(accessToken == null || !validateToken(accessToken)) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
        return getAuthentication(accessToken);
    }

    // RefreshToken 유효성 확인
    public void validateRefreshToken(String refreshToken) {
        // 레디스에 값이 있는지 확인
        boolean exists = refreshTokenRepository.existsById(refreshToken);

        if(!exists) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
    }

    // 토큰의 클레임 가져오기
    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
    }

    // 토큰 재발급
    public MemberResponseDTO.LoginResultDTO reissueToken(String refreshToken) {

        RefreshToken refreshTokenEntity = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.INVALID_TOKEN));

        Member member = memberRepository.findById(refreshTokenEntity.getUserId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 새로운 인증 객체 Authentication 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                null,
                Collections.singleton(() -> member.getRole().name())
        );

        // 새로운 AccessToken, RefreshToken 생성
        String newAccessToken = generateToken(authentication);
        String newRefreshToken = generateRefreshToken(authentication);

        // 기존 RefreshToken 삭제
        refreshTokenRepository.delete(refreshTokenEntity);
        // 새로운 RefreshToken 생성 및 저장
        RefreshToken newRefreshTokenEntity = new RefreshToken(newRefreshToken, member.getId());
        refreshTokenRepository.save(newRefreshTokenEntity);

        return MemberConverter.toLoginResultDTO(
                member.getId(),
                newAccessToken,
                newRefreshToken
        );
    }
}
