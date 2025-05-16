package umc8th.spring8th.repository.FoodCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring8th.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
