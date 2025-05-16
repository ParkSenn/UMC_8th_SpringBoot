package umc8th.spring8th.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.converter.ReviewConverter;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.service.ReviewService.ReviewCommandService;
import umc8th.spring8th.validation.annotation.ExistStore;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;
import umc8th.spring8th.web.dto.Review.ReviewResponseDTO;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 후기 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid ReviewRequestDTO.NewReviewDTO request,
                                                       @PathVariable(name = "storeId") @ExistStore Long storeId) {

        Review review = reviewCommandService.createReview(request, storeId);

        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResultDTO(review));
    }
}
