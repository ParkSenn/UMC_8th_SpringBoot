package umc8th.spring8th.service.MemberService;

import umc8th.spring8th.domain.Member;
import umc8th.spring8th.web.dto.Member.MemberRequestDTO;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
