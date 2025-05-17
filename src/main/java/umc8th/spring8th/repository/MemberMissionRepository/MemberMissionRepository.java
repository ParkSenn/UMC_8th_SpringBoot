package umc8th.spring8th.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc8th.spring8th.domain.enums.MissionStatus;
import umc8th.spring8th.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    @Modifying
    @Query("DELETE FROM MemberMission mm WHERE mm.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status
    );
}
