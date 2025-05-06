package umc8th.spring8th.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.repository.MissionRepository.MissionRepository;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    // 홈 화면 - 특정 지역의 회원의 도전 가능한 미션 모아보기
    @Override
    public List<MissionResponseDTO.RegionMissionDTO> findAvailableMissions(Long memberId, String regionName) {

        List<MissionResponseDTO.RegionMissionDTO> memberMissionDTOS = missionRepository.findAvailableMissions(memberId, regionName);

        return memberMissionDTOS;
    }
}
