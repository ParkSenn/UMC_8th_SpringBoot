package umc8th.spring8th.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc8th.spring8th.validation.validator.PageNumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageNumValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageNum {

    String message() default "page는 1 이상의 정수여야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
