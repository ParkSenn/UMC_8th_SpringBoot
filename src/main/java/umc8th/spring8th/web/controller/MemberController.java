package umc8th.spring8th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring8th.service.MemberService.MemberQueryService;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;

    // 마이페이지
    @GetMapping("/member/{memberId}/my-page")
    public MemberResponseDTO.MemberMyPageInfoDTO getMyPageInfo(@PathVariable Long memberId) {

        return memberQueryService.findMyPageInfo(memberId);
    }
}
