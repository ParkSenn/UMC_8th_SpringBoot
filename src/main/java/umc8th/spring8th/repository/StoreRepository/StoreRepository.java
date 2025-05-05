package umc8th.spring8th.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring8th.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
