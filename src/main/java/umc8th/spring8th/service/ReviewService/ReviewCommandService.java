package umc8th.spring8th.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

public interface ReviewCommandService {

    // 후기 작성
    Review createReview(ReviewRequestDTO.NewReviewDTO request, Long storeId, MultipartFile reviewPicture);
}
