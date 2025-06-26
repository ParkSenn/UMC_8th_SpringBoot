package umc8th.spring8th.repository.UuidRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring8th.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
