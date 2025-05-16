package umc8th.spring8th.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc8th.spring8th.domain.FoodCategory;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.common.BaseEntity;

import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MemberPrefer가 연관관계 주인
    // Member 테이블을 FK(member_id)로 참조하고 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // MemberPrefer가 연관관계 주인
    // FoodCategory 테이블을 FK(category_id)로 참조하고 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    // Set의 중복 검사를 위한 오버라이드 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberPrefer)) return false;
        MemberPrefer that = (MemberPrefer) o;
        return Objects.equals(id, that.id);
    }

    public void setMember(Member member) {
        if(this.member != null) {
            member.getMemberPreferList().remove(this);
        }
        this.member = member;
        member.getMemberPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

