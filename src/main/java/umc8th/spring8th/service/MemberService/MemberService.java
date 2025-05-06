package umc8th.spring8th.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.repository.MemberAgreeRepository.MemberAgreeRepository;
import umc8th.spring8th.repository.MemberMissionRepository.MemberMissionRepository;
import umc8th.spring8th.repository.MemberPreferRepository.MemberPreferRepository;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.ProfileImageRepository.ProfileImageRepository;
import umc8th.spring8th.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberPreferRepository memberPreferRepository;
    private final MemberAgreeRepository memberAgreeRepository;
    private final ProfileImageRepository profileImageRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void withdrawMember (Long memberId) {

        memberAgreeRepository.deleteByMemberId(memberId);
        memberMissionRepository.deleteByMemberId(memberId);
        memberPreferRepository.deleteByMemberId(memberId);
        profileImageRepository.deleteByMemberId(memberId);
        reviewRepository.deleteByMemberId(memberId);

        memberRepository.deleteById(memberId);
    }
}
