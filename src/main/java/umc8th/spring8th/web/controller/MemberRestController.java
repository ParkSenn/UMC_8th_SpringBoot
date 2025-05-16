package umc8th.spring8th.web.controller;

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
    @PostMapping("/members")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request) {

        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}
