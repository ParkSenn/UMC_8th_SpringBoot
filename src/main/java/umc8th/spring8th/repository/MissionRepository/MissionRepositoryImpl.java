package umc8th.spring8th.repository.MissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.QMission;
import umc8th.spring8th.domain.QRegion;
import umc8th.spring8th.domain.QStore;
import umc8th.spring8th.domain.mapping.QMemberMission;
import umc8th.spring8th.web.dto.Mission.MissionResponseDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;

    // 홈 화면 - 특정 지역의 회원의 도전 가능한 미션 모아보기
    @Override
    public List<MissionResponseDTO.RegionMissionDTO> findAvailableMissions(Long memberId, String regionName) {

        List<MissionResponseDTO.RegionMissionDTO> result = jpaQueryFactory
                .select(Projections.constructor(
                        MissionResponseDTO.RegionMissionDTO.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        Expressions.numberTemplate(Integer.class,
                                "datediff({0}, current_date)", mission.deadline)
                ))
                .from(mission)
                .join(store).on(mission.store.id.eq(store.id))
                .join(region).on(region.id.eq(store.region.id))
                .where(
                        region.name.eq(regionName),
                        mission.deadline.gt(LocalDate.now()),
                        mission.id.notIn(
                                JPAExpressions
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )
                )
                .orderBy(
                        Expressions.numberTemplate(Integer.class,
                                "datediff({0}, current_date)", mission.deadline).asc()
                )
                .fetch();

        return result;
    }
}
