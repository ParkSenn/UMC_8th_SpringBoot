package umc8th.spring8th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.domain.ProfileImage;
import umc8th.spring8th.repository.*;

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
