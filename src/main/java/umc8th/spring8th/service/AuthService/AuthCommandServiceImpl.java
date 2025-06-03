package umc8th.spring8th.service.AuthService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.apiPayload.exception.handler.MemberHandler;
import umc8th.spring8th.config.security.jwt.JwtTokenProvider;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public MemberResponseDTO.LoginResultDTO reissueToken(String refreshToken) {
        try {
            jwtTokenProvider.validateRefreshToken(refreshToken);
            return jwtTokenProvider.reissueToken(refreshToken);
        } catch (ExpiredJwtException eje) {
            throw new MemberHandler(ErrorStatus.TOKEN_EXPIRED);
        } catch (IllegalArgumentException iae) {
            throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
        }
    }

}
