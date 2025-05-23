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
import umc8th.spring8th.converter.MissionConverter;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.service.MissionService.MissionCommandService;
import umc8th.spring8th.service.MissionService.MissionQueryService;
import umc8th.spring8th.validation.annotation.ExistStore;
import umc8th.spring8th.validation.annotation.ValidPageNum;
import umc8th.spring8th.web.dto.Mission.MissionRequestDTO;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    // 홈 화면 - 특정 지역의 회원의 도전 가능한 미션 모아보기
    @GetMapping("/member/{memberId}/available-missions")
    public ApiResponse<List<MissionResponseDTO.RegionMissionDTO>> getAvailableMissions(@PathVariable Long memberId, @RequestParam String regionName) {

        return ApiResponse.onSuccess(missionQueryService.findAvailableMissions(memberId, regionName));
    }

    // 가게에 미션 추가
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.NewMissionResultDTO> createMission(@PathVariable(name = "storeId") @ExistStore Long storeId,
                                                                             @RequestBody @Valid MissionRequestDTO.NewMissionDTO request) {

        Mission mission = missionCommandService.createMission(storeId, request);

        return ApiResponse.onSuccess(MissionConverter.toNewMissionResultDTO(mission));
    }

    // 특정 가게 미션 목록 보기
    @GetMapping("/stores/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이징을 위한 페이지 번호, 1 이상의 정수, queryString 입니다!"),
            @Parameter(name = "size", description = "페이징을 위한 크기 지정, 1 이상의 정수, queryString 입니다!")
    })
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getStoreMissions(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                @ValidPageNum @RequestParam(name = "page") Integer page,
                                                                                @RequestParam(name = "size", defaultValue = "10") Integer size) {

        Page<Mission> missionPage = missionQueryService.findStoreMissions(storeId, page, size);

        return ApiResponse.onSuccess(MissionConverter.toStoreMissionListDTO(missionPage));
    }
}
