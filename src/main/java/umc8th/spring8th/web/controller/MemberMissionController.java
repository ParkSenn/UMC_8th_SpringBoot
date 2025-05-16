package umc8th.spring8th.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.apiPayload.ApiResponse;
import umc8th.spring8th.domain.mapping.MemberMission;
import umc8th.spring8th.service.MemberMissionService.MemberMissionQueryService;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;

    // 특정 회원의 진행 중인 미션 모아보기
    @GetMapping("/member/{memberId}/challenging-missions")
    public ApiResponse<List<MemberMissionResponseDTO.MemberMissionDTO>> getChallengingMissions(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberMissionQueryService.findChallengingMissions(memberId));
    }

    // 특정 회원의 진행 완료한 미션 모아보기
    @GetMapping("/member/{memberId}/completed-missions")
    public ApiResponse<List<MemberMissionResponseDTO.MemberMissionDTO>> getCompletedMissions(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberMissionQueryService.findCompletedMissions(memberId));
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<MemberMissionResponseDTO.NewChallengingMemberMissionResultDTO> challengeMission(@PathVariable(name = "missionId") Long missionId,
                                                                                              @RequestBody @Valid MemberMissionRequestDTO.NewChallengingMemberMissionDTO request) {

        MemberMission memberMission = memberMissionCommandService.challengeMission(missionId, request);
        return ApiResponse.onSuccess(MemberMissionConverter.toNewChallengingMemberMissionResultDTO(memberMission));
    }


}
