package umc8th.spring8th.service.FoodCategoryService;

import java.util.List;

public interface FoodCategoryValidationService {

    // 회원가입 시, 존재하는 FoodCategory id인지 검증
    boolean isCategoriesExist(List<Long> categoryIds);

}
