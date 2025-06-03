package umc8th.spring8th.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.service.AuthService.AuthCommandService;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthCommandService authCommandService;

    @Operation(summary = "토큰 재발급", description = "accessToken이 만료 시 refreshToken을 통해 accessToken을 재발급합니다.")
    @PostMapping("/reissue")
    @Parameter(name = "refreshToken", description = "리프레시 토큰")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> reissueToken(@RequestHeader("refreshToken") String refreshToken) {

        return ApiResponse.onSuccess(authCommandService.reissueToken(refreshToken));
    }
}
