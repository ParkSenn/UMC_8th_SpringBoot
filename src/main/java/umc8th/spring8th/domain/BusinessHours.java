package umc8th.spring8th.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc8th.spring8th.domain.common.BaseEntity;
import umc8th.spring8th.domain.enums.Weekday;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BusinessHours extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'MONDAY'")
    private Weekday weekday;

    private LocalTime openTime;

    private LocalTime closeTime;

    @ColumnDefault("FALSE")
    private Boolean isOffDay;
}
