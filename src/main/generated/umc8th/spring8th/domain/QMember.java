package umc8th.spring8th.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1961805816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final umc8th.spring8th.domain.common.QBaseEntity _super = new umc8th.spring8th.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc8th.spring8th.domain.enums.Gender> gender = createEnum("gender", umc8th.spring8th.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final ListPath<umc8th.spring8th.domain.mapping.MemberAgree, umc8th.spring8th.domain.mapping.QMemberAgree> memberAgreeList = this.<umc8th.spring8th.domain.mapping.MemberAgree, umc8th.spring8th.domain.mapping.QMemberAgree>createList("memberAgreeList", umc8th.spring8th.domain.mapping.MemberAgree.class, umc8th.spring8th.domain.mapping.QMemberAgree.class, PathInits.DIRECT2);

    public final ListPath<umc8th.spring8th.domain.mapping.MemberMission, umc8th.spring8th.domain.mapping.QMemberMission> memberMissionList = this.<umc8th.spring8th.domain.mapping.MemberMission, umc8th.spring8th.domain.mapping.QMemberMission>createList("memberMissionList", umc8th.spring8th.domain.mapping.MemberMission.class, umc8th.spring8th.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final ListPath<umc8th.spring8th.domain.mapping.MemberPrefer, umc8th.spring8th.domain.mapping.QMemberPrefer> memberPreferList = this.<umc8th.spring8th.domain.mapping.MemberPrefer, umc8th.spring8th.domain.mapping.QMemberPrefer>createList("memberPreferList", umc8th.spring8th.domain.mapping.MemberPrefer.class, umc8th.spring8th.domain.mapping.QMemberPrefer.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final QProfileImage profileImage;

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc8th.spring8th.domain.enums.Role> role = createEnum("role", umc8th.spring8th.domain.enums.Role.class);

    public final EnumPath<umc8th.spring8th.domain.enums.SocialType> socialType = createEnum("socialType", umc8th.spring8th.domain.enums.SocialType.class);

    public final StringPath specAddress = createString("specAddress");

    public final EnumPath<umc8th.spring8th.domain.enums.MemberStatus> status = createEnum("status", umc8th.spring8th.domain.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profileImage = inits.isInitialized("profileImage") ? new QProfileImage(forProperty("profileImage"), inits.get("profileImage")) : null;
    }

}

