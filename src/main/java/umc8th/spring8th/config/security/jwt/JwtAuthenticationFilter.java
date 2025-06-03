package umc8th.spring8th.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import umc8th.spring8th.config.properties.Constants;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    // HttpServletRequest 로 받아온 요청 객체에서 순수한 토큰을 추출
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        // jwtTokenProvider의 validateToken()에서 순수한 토큰 검증 과정 통과
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // getAuthentication() 메소드를 호출해서 인증 객체 만듦
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContextHolder가 인증을 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 다음 필터, 컨트롤러 등에 요청을 넘겨줌
        filterChain.doFilter(request, response);
    }

    // 순수 토큰 반환
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }
}
