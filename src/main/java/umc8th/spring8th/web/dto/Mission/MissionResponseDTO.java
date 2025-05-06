package umc8th.spring8th.web.dto.Mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionMissionDTO {
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private Integer daysLeft;
    }
}
