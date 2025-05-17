package umc8th.spring8th.service.MemberMissionService;

import umc8th.spring8th.domain.mapping.MemberMission;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

public interface MemberMissionCommandService {

    MemberMission challengeMission(MemberMissionRequestDTO.NewChallengingMemberMissionDTO request);
}
