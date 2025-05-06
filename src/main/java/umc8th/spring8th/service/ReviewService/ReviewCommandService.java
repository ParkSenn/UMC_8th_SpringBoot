package umc8th.spring8th.service.ReviewService;

import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

public interface ReviewCommandService {

    // 후기 작성
    void createReview(ReviewRequestDTO.NewReviewDTO request);
}
