package umc8th.spring8th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring8th.service.ReviewService.ReviewCommandService;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 후기 작성
    @PostMapping("/reviews")
    public void createReview(@RequestBody ReviewRequestDTO.NewReviewDTO request) {

        reviewCommandService.createReview(request);
    }
}
