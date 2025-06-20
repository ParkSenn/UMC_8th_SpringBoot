package umc8th.spring8th.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessHours is a Querydsl query type for BusinessHours
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessHours extends EntityPathBase<BusinessHours> {

    private static final long serialVersionUID = 1934486017L;

    public static final QBusinessHours businessHours = new QBusinessHours("businessHours");

    public final umc8th.spring8th.domain.common.QBaseEntity _super = new umc8th.spring8th.domain.common.QBaseEntity(this);

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOffDay = createBoolean("isOffDay");

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<umc8th.spring8th.domain.enums.Weekday> weekday = createEnum("weekday", umc8th.spring8th.domain.enums.Weekday.class);

    public QBusinessHours(String variable) {
        super(BusinessHours.class, forVariable(variable));
    }

    public QBusinessHours(Path<? extends BusinessHours> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessHours(PathMetadata metadata) {
        super(BusinessHours.class, metadata);
    }

}

