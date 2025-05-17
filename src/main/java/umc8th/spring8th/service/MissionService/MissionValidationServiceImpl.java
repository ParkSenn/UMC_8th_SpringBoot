package umc8th.spring8th.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.repository.MissionRepository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionValidationServiceImpl implements MissionValidationService {

    private final MissionRepository missionRepository;

    @Override
    public boolean isMissionExist(Long missionId) {

        return missionRepository.existsById(missionId);
    }

}
