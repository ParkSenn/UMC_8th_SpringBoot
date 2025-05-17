package umc8th.spring8th.service.MemberMissionService;

import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

public interface MemberMissionValidationService {

    boolean isChallengingMemberMissionExist(MemberMissionRequestDTO.NewChallengingMemberMissionDTO request);
}
