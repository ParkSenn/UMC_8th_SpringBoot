package umc8th.spring8th.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    // 마이페이지
    @Override
    public MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId) {

        return memberRepository.findMyPageInfo(memberId);
    }
}
