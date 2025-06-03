package umc8th.spring8th.service.AuthService;

import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

public interface AuthCommandService {

    MemberResponseDTO.LoginResultDTO reissueToken(String refreshToken);
}
