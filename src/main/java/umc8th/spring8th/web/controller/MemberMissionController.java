package umc8th.spring8th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring8th.service.MemberMissionService.MemberMissionQueryService;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionQueryService memberMissionQueryService;

    // 특정 회원의 진행 중인 미션 모아보기
    @GetMapping("/member/{memberId}/challenging-missions")
    public List<MemberMissionResponseDTO.MemberMissionDTO> getChallengingMissions(@PathVariable Long memberId) {
        return memberMissionQueryService.findChallengingMissions(memberId);
    }

    // 특정 회원의 진행 완료한 미션 모아보기
    @GetMapping("/member/{memberId}/completed-missions")
    public List<MemberMissionResponseDTO.MemberMissionDTO> getCompletedMissions(@PathVariable Long memberId) {
        return memberMissionQueryService.findCompletedMissions(memberId);
    }


}
