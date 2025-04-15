package umc8th.spring8th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.mapping.MemberAgree;

@Repository
public interface MemberAgreeRepository extends JpaRepository<MemberAgree, Long> {

    @Modifying
    @Query("DELETE FROM MemberAgree ma WHERE ma.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}
