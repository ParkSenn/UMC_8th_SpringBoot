package umc8th.spring8th.converter;

import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.web.dto.Mission.MissionRequestDTO;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toMission(Store store, MissionRequestDTO.NewMissionDTO request) {

        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .reward(request.getReward())
                .build();
    }

    public static MissionResponseDTO.NewMissionResultDTO toNewMissionResultDTO(Mission mission) {

        return MissionResponseDTO.NewMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
