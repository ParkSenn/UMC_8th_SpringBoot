package umc8th.spring8th.service.MemberService;

import umc8th.spring8th.domain.Member;
import umc8th.spring8th.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);
}
