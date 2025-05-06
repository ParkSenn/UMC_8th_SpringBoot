package umc8th.spring8th.repository.MemberPreferRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc8th.spring8th.domain.mapping.MemberPrefer;

public interface MemberPreferRepository extends JpaRepository<MemberPrefer, Long> {

    @Modifying
    @Query("DELETE FROM MemberPrefer mp WHERE mp.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}
