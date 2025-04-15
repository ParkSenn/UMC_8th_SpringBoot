package umc8th.spring8th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
