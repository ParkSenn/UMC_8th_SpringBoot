package umc8th.spring8th.converter;

import org.springframework.data.domain.Page;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.web.dto.Mission.MissionRequestDTO;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class MissionConverter {

    public static Mission toMission(Store store, MissionRequestDTO.NewMissionDTO request) {

        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .reward(request.getReward())
                .build();
    }

    public static MissionResponseDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {

        return MissionResponseDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadLine(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> missionPage) {

        List<MissionResponseDTO.StoreMissionDTO> storeMissionDTOList = missionPage.stream()
                .map(MissionConverter::toStoreMissionDTO).toList();

        return MissionResponseDTO.StoreMissionListDTO.builder()
                .storeMissionDTOList(storeMissionDTOList)
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(storeMissionDTOList.size())
                .build();
    }

    public static MissionResponseDTO.NewMissionResultDTO toNewMissionResultDTO(Mission mission) {

        return MissionResponseDTO.NewMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
