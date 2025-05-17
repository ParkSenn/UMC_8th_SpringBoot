package umc8th.spring8th.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc8th.spring8th.validation.validator.ChallengingMemberMissionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ChallengingMemberMissionExistValidator.class)
@Target(ElementType.TYPE) // 클래스 레벨 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistChallengingMemberMission {

    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
