package umc8th.spring8th.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring8th.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
