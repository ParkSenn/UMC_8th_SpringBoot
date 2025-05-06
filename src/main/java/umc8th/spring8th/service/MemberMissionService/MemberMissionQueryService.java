package umc8th.spring8th.service.MemberMissionService;

import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

public interface MemberMissionQueryService {

    // 특정 회원의 진행 중인 미션 모아보기
    List<MemberMissionResponseDTO.MemberMissionDTO> findChallengingMissions(Long memberId);

    // 특정 회원의 진행 완료한 미션 모아보기
    List<MemberMissionResponseDTO.MemberMissionDTO> findCompletedMissions(Long memberId);

}
