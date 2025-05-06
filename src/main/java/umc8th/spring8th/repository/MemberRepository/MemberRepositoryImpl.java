package umc8th.spring8th.repository.MemberRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc8th.spring8th.domain.QMember;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    // 마이페이지
    @Override
    public MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId) {

        MemberResponseDTO.MemberMyPageInfoDTO result = jpaQueryFactory
                .select(Projections.constructor(
                        MemberResponseDTO.MemberMyPageInfoDTO.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phoneNum,
                        member.point
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        return result;
    }
}
