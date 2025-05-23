package umc8th.spring8th.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.ReviewRepository.ReviewRepository;
import umc8th.spring8th.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page - 1, 10));

        return storePage;
    }

    @Override
    public Page<Review> getMemberReviewList(Long memberId, Integer page, Integer size) {

        Member member = memberRepository.getReferenceById(memberId);

        Page<Review> reviewPage = reviewRepository.findAllByMember(member, PageRequest.of(page - 1, size));

        return reviewPage;
    }
}
