package umc8th.spring8th.web.dto.MemberMission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberMissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDTO {
        private Long missionId;
        private String storeName;
        private Integer reward;
        private String missionSpec;
        private String status;
    }
}
