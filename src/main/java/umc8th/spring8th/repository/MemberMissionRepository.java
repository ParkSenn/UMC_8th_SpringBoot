package umc8th.spring8th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.mapping.MemberAgree;
import umc8th.spring8th.domain.mapping.MemberMission;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Modifying
    @Query("DELETE FROM MemberMission mm WHERE mm.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}
