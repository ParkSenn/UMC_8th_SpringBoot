package umc8th.spring8th.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

public interface MemberQueryService {

    // 마이페이지
    MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
