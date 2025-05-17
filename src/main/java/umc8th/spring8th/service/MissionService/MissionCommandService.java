package umc8th.spring8th.service.MissionService;

import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.web.dto.Mission.MissionRequestDTO;

public interface MissionCommandService {

    Mission createMission(Long storeId, MissionRequestDTO.NewMissionDTO request);
}
