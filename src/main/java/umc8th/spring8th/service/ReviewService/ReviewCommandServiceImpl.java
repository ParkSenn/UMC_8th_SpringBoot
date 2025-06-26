package umc8th.spring8th.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc8th.spring8th.aws.s3.AmazonS3Manager;
import umc8th.spring8th.converter.ReviewConverter;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.domain.Uuid;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.ReviewImageRepository.ReviewImageRepository;
import umc8th.spring8th.repository.ReviewRepository.ReviewRepository;
import umc8th.spring8th.repository.StoreRepository.StoreRepository;
import umc8th.spring8th.repository.UuidRepository.UuidRepository;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final AmazonS3Manager s3Manager;
    private final ReviewImageRepository reviewImageRepository;
    private final UuidRepository uuidRepository;

    // 후기 작성
    @Override
    @Transactional
    public Review createReview(ReviewRequestDTO.NewReviewDTO request, Long storeId, MultipartFile reviewPicture) {

//        Member member = memberRepository.findById(request.getMemberId())
//                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Member member = memberRepository.getReferenceById(request.getMemberId());
//        Store store = storeRepository.findById(storeId)
//                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Store store = storeRepository.getReferenceById(storeId);

        Review review = ReviewConverter.toReview(request, member, store);

        String uuid = UUID.randomUUID().toString();
        Uuid saveUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(saveUuid), reviewPicture);

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        return reviewRepository.save(review);
    }
}
