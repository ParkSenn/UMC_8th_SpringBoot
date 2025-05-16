package umc8th.spring8th.web.dto.Review;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc8th.spring8th.validation.annotation.ExistMember;

public class ReviewRequestDTO {

    @Getter
    public static class NewReviewDTO {
        @ExistMember
        private Long memberId;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotNull(message = "점수는 필수 입력 값입니다.")
        @DecimalMin(value = "0.0", inclusive = true, message = "점수는 0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", inclusive = true, message = "점수는 5 이하이어야 합니다.")
        private Float score;
    }
}
