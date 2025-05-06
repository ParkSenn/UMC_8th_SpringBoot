package umc8th.spring8th.service.MemberService;

import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

public interface MemberQueryService {

    // 마이페이지
    MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId);
}
