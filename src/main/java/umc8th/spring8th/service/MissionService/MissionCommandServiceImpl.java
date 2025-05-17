package umc8th.spring8th.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.converter.MissionConverter;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.Store;
import umc8th.spring8th.repository.MissionRepository.MissionRepository;
import umc8th.spring8th.repository.StoreRepository.StoreRepository;
import umc8th.spring8th.web.dto.Mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Mission createMission(Long storeId, MissionRequestDTO.NewMissionDTO request) {

        Store store = storeRepository.getReferenceById(storeId);

        Mission newMission = MissionConverter.toMission(store, request);

        return missionRepository.save(newMission);
    }

}
