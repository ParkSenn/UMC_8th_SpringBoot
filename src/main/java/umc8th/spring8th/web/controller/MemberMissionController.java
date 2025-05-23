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
import umc8th.spring8th.converter.MemberMissionConverter;
import umc8th.spring8th.domain.mapping.MemberMission;
import umc8th.spring8th.service.MemberMissionService.MemberMissionCommandService;
import umc8th.spring8th.service.MemberMissionService.MemberMissionQueryService;
import umc8th.spring8th.validation.annotation.ExistMember;
import umc8th.spring8th.validation.annotation.ValidPageNum;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    // 특정 회원의 진행 중인 미션 모아보기
    @GetMapping("/member/{memberId}/challenging-missions")
    @Operation(summary = "특정 회원의 진행 중인 미션 목록 조회 API",description = "특정 회원의 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
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
    public ApiResponse<MemberMissionResponseDTO.MemberMissionListDTO> getChallengingMissions(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                               @ValidPageNum @RequestParam(name = "page") Integer page,
                                                                                               @RequestParam(name = "size", defaultValue = "10") Integer size) {

        Page<MemberMissionResponseDTO.MemberMissionDTO> pageResult = memberMissionQueryService.findChallengingMissions(memberId, page, size);

        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionListDTO(pageResult));
    }

    // 특정 회원의 진행 완료한 미션 모아보기
    @GetMapping("/member/{memberId}/completed-missions")
    public ApiResponse<List<MemberMissionResponseDTO.MemberMissionDTO>> getCompletedMissions(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberMissionQueryService.findCompletedMissions(memberId));
    }

    @PostMapping("/missions/challenge")
    public ApiResponse<MemberMissionResponseDTO.NewChallengingMemberMissionResultDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.NewChallengingMemberMissionDTO request) {

        MemberMission memberMission = memberMissionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toNewChallengingMemberMissionResultDTO(memberMission));
    }


}
