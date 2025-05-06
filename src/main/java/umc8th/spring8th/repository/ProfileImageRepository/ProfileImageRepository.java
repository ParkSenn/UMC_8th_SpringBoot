package umc8th.spring8th.repository.ProfileImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc8th.spring8th.domain.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    @Modifying
    @Query("DELETE FROM ProfileImage pi WHERE pi.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}
