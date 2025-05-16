package umc8th.spring8th.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryValidationServiceImpl implements FoodCategoryValidationService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean isCategoriesExist(List<Long> categoryIds) {

        return categoryIds.stream().allMatch(foodCategoryRepository::existsById);
    }

}
