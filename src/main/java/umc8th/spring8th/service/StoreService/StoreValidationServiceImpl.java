package umc8th.spring8th.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreValidationServiceImpl implements StoreValidationService {

    private final StoreRepository storeRepository;
    @Override
    public boolean isStoreExist(Long storeId) {

        return storeRepository.existsById(storeId);
    }
}
