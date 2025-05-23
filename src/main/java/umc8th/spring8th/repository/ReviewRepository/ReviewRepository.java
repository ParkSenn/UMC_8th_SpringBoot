package umc8th.spring8th.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc8th.spring8th.domain.Review;
import umc8th.spring8th.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("DELETE FROM Review r WHERE r.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
