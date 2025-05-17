package umc8th.spring8th.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.converter.MissionConverter;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.service.MissionService.MissionQueryService;
import umc8th.spring8th.validation.annotation.ExistStore;
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
}
