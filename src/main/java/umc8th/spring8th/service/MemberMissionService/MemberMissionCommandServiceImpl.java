package umc8th.spring8th.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.converter.MemberMissionConverter;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.mapping.MemberMission;
import umc8th.spring8th.repository.MemberMissionRepository.MemberMissionRepository;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.MissionRepository.MissionRepository;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    p

    @Override
    @Transactional
    public MemberMission challengeMission(MemberMissionRequestDTO.NewChallengingMemberMissionDTO request) {

        Member member = memberRepository.getReferenceById(request.getMemberId());
        Mission mission = missionRepository.getReferenceById(request.getMissionId());

        MemberMission newMemberMission = MemberMissionConverter.toChallengingMemberMission(member, mission);

        return memberMissionRepository.save(newMemberMission);
    }
}
