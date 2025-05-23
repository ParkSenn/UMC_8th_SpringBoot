package umc8th.spring8th.service.MissionService;

import org.springframework.data.domain.Page;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.util.List;

public interface MissionQueryService {

    // 홈 화면 - 특정 지역의 회원의 도전 가능한 미션 모아보기
    List<MissionResponseDTO.RegionMissionDTO> findAvailableMissions(Long memberId, String regionName);

    // 특정 가게의 미션 모아보기
    Page<Mission> findStoreMissions(Long storeId, Integer page, Integer size);
}
