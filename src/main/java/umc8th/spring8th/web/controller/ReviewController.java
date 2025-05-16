package umc8th.spring8th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.service.ReviewService.ReviewCommandService;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 후기 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<String> createReview(@RequestBody ReviewRequestDTO.NewReviewDTO request, @PathVariable Long storeId) {

        reviewCommandService.createReview(request, storeId);

        return ApiResponse.onSuccess("리뷰가 성공적으로 등록되었습니다.");
    }
}
