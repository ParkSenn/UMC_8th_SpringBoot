package umc8th.spring8th.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.ReviewRepository.ReviewRepository;
import umc8th.spring8th.repository.StoreRepository.StoreRepository;
import umc8th.spring8th.converter.ReviewConverter;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 후기 작성
    @Override
    public void createReview(ReviewRequestDTO.NewReviewDTO request, Long storeId) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = ReviewConverter.toReview(request, member, store);

        reviewRepository.save(review);
    }
}
