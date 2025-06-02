package umc8th.spring8th.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.enums.MemberStatus;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    // 메서드 이름으로 쿼리 생성
//    List<Member> findByNameAndStatus(String name, MemberStatus status);

  // @Query 어노테이션으로 JPQL 직접 작성
    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.status = :status")
    List<Member> findByNameAndStatus(@Param("name") String name, @Param("status") MemberStatus status);

    Optional<Member> findByEmail(String email);
}
