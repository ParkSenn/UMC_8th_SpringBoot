package umc8th.spring8th.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.domain.enums.MissionStatus;
import umc8th.spring8th.repository.MemberMissionRepository.MemberMissionRepository;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionValidationServiceImpl implements MemberMissionValidationService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean alreadyChallengingMission(MemberMissionRequestDTO.NewChallengingMemberMissionDTO request) {

        return memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(request.getMemberId(), request.getMissionId(), MissionStatus.CHALLENGING);
    }
}
