package umc8th.spring8th.converter;

import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.NewReviewDTO request, Member member, Store store) {

        return Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }
}
