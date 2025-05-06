package umc8th.spring8th.repository.MemberRepository;

import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

public interface MemberRepositoryCustom {

    // 마이페이지
    MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId);

}
