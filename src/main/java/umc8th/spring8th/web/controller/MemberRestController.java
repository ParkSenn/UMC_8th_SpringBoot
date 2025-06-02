package umc8th.spring8th.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.converter.MemberConverter;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.service.MemberService.MemberCommandService;
import umc8th.spring8th.service.MemberService.MemberQueryService;
import umc8th.spring8th.web.dto.Member.MemberRequestDTO;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;


    // 마이페이지
    @GetMapping("/member/{memberId}/my-page")
    public ApiResponse<MemberResponseDTO.MemberMyPageInfoDTO> getMyPageInfo(@PathVariable Long memberId) {

        return ApiResponse.onSuccess(memberQueryService.findMyPageInfo(memberId));
    }

    // 회원가입
    @PostMapping("/members/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {

        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    // 로그인
    @PostMapping("/members/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    // 내 정보 조회
    @GetMapping("/members/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API 입니다.",
            security = {@SecurityRequirement(name = "JWT TOKEN")}
    )
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }
}
