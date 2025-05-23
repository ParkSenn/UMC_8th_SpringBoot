package umc8th.spring8th.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

public interface MemberMissionRepositoryCustom {

    // 특정 회원의 진행 중인 미션 모아보기
    Page<MemberMissionResponseDTO.MemberMissionDTO> findChallengingMissions(Long memberId, Pageable pageable);

    // 특정 회원의 진행 완료한 미션 모아보기
    List<MemberMissionResponseDTO.MemberMissionDTO> findCompletedMissions(Long memberId);
}
