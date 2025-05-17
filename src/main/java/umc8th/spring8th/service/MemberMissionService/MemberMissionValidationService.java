package umc8th.spring8th.service.MemberMissionService;

import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

public interface MemberMissionValidationService {

    boolean alreadyChallengingMission(MemberMissionRequestDTO.NewChallengingMemberMissionDTO request);
}
