package umc8th.spring8th.service.ReviewService;

import org.springframework.data.domain.Page;
import umc8th.spring8th.domain.Review;

public interface ReviewQueryService {

    Page<Review> getReviewList(Long storeId, Integer page);
}
