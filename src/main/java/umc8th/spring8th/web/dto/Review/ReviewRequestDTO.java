package umc8th.spring8th.web.dto.Review;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class NewReviewDTO {
        private Long memberId;
        private Long storeId;
        private String content;
        private String title;
        private Float score;
    }
}
