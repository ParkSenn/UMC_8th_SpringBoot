package umc8th.spring8th.repository.RefreshTokenRepository;

import org.springframework.data.repository.CrudRepository;
import umc8th.spring8th.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
