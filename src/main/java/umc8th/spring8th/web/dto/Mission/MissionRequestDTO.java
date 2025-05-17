package umc8th.spring8th.web.dto.Mission;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class NewMissionDTO {
        @NotBlank
        String missionSpec;
        @NotBlank
        LocalDate deadline;
        @DecimalMin(value = "0.0", inclusive = true, message = "점수는 0 이상이어야 합니다.")
        Integer reward;
    }
}
