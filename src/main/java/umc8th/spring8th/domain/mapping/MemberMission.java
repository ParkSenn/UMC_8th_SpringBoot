package umc8th.spring8th.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.common.BaseEntity;
import umc8th.spring8th.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
