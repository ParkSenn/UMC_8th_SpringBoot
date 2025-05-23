package umc8th.spring8th.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.converter.ReviewConverter;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.service.ReviewService.ReviewCommandService;
import umc8th.spring8th.service.ReviewService.ReviewQueryService;
import umc8th.spring8th.validation.annotation.ExistMember;
import umc8th.spring8th.validation.annotation.ExistStore;
import umc8th.spring8th.validation.annotation.ValidPageNum;
import umc8th.spring8th.web.dto.Review.ReviewRequestDTO;
import umc8th.spring8th.web.dto.Review.ReviewResponseDTO;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 후기 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.NewReviewResultDTO> createReview(@RequestBody @Valid ReviewRequestDTO.NewReviewDTO request,
                                                                          @PathVariable(name = "storeId") @ExistStore Long storeId) {

        Review review = reviewCommandService.createReview(request, storeId);

        return ApiResponse.onSuccess(ReviewConverter.toNewReviewResultDTO(review));
    }

    // 특정 가게의 댓글 가져오기
    @GetMapping("/stores/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이징을 위한 페이지 번호, 1 이상의 정수, queryString 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @ValidPageNum @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = reviewQueryService.getReviewList(storeId, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }

    // 특정 회원의 댓글 가져오기
    @GetMapping("/members/{memberId}/reviews")
    @Operation(summary = "특정 회원의 리뷰 목록 조회 API",description = "특정 회원의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이징을 위한 페이지 번호, 1 이상의 정수, queryString 입니다!"),
            @Parameter(name = "size", description = "페이징을 위한 크기 지정, 1 이상의 정수, queryString 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMemberReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                   @ValidPageNum @RequestParam(name = "page") Integer page,
                                                                                   @RequestParam(name = "size", defaultValue = "10") Integer size) {

        Page<Review> reviewList = reviewQueryService.getMemberReviewList(memberId, page, size);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(reviewList));
    }
}
