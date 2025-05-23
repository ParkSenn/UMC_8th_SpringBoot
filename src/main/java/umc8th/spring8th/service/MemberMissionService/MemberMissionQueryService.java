package umc8th.spring8th.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

public interface MemberMissionQueryService {

    // 특정 회원의 진행 중인 미션 모아보기
    Page<MemberMissionResponseDTO.MemberMissionDTO> findChallengingMissions(Long memberId, Integer page, Integer size);

    // 특정 회원의 진행 완료한 미션 모아보기
    List<MemberMissionResponseDTO.MemberMissionDTO> findCompletedMissions(Long memberId);

}
