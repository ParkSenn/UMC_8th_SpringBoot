package umc8th.spring8th.repository.MemberMissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.QMember;
import umc8th.spring8th.domain.QMission;
import umc8th.spring8th.domain.QStore;
import umc8th.spring8th.domain.enums.MissionStatus;
import umc8th.spring8th.domain.mapping.QMemberMission;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QStore store = QStore.store;

    // 특정 회원의 진행 중인 미션 모아보기
    @Override
    public Page<MemberMissionResponseDTO.MemberMissionDTO> findChallengingMissions(Long memberId, Pageable pageable) {

        List<MemberMissionResponseDTO.MemberMissionDTO> result = jpaQueryFactory
                .select(Projections.constructor(
                        MemberMissionResponseDTO.MemberMissionDTO.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        memberMission.status.stringValue()
                ))
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(memberMission.mission.id.eq(mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(
                        member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 구하기
        long total = jpaQueryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING)
                )
                .fetchOne();

        return new PageImpl<>(result, pageable, total);
    }

    // 특정 회원의 진행 완료한 미션 모아보기
    @Override
    public List<MemberMissionResponseDTO.MemberMissionDTO> findCompletedMissions(Long memberId) {

        List<MemberMissionResponseDTO.MemberMissionDTO> result = jpaQueryFactory
                .select(Projections.constructor(
                        MemberMissionResponseDTO.MemberMissionDTO.class,
                        mission.id,
                        store.name,
                        mission.reward,
                        mission.missionSpec,
                        memberMission.status.stringValue()
                ))
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(memberMission.mission.id.eq(mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(
                        member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE)
                )
                .fetch();

        return result;
    }
}
