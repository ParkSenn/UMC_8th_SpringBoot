package umc8th.spring8th.web.dto.Review;

import jakarta.validation.constraints.NotBlank;
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
        @NotBlank
        private Float score;
    }
}
