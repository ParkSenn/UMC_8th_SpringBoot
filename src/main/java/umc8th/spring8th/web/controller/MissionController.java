package umc8th.spring8th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8th.spring8th.service.MissionService.MissionQueryService;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 홈 화면 - 특정 지역의 회원의 도전 가능한 미션 모아보기
    @GetMapping("/member/{memberId}/available-missions")
    public List<MissionResponseDTO.RegionMissionDTO> getAvailableMissions(@PathVariable Long memberId, @RequestParam String regionName) {

        return missionQueryService.findAvailableMissions(memberId, regionName);
    }
}
