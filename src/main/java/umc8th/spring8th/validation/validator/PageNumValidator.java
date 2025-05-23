package umc8th.spring8th.validation.validator;

import com.google.common.math.DoubleMath;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.validation.annotation.ValidPageNum;

@Component
@RequiredArgsConstructor
public class PageNumValidator implements ConstraintValidator<ValidPageNum, Integer> {
    @Override
    public void initialize(ValidPageNum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer pageNum, ConstraintValidatorContext context) {
        boolean isValid = pageNum > 0 && DoubleMath.isMathematicalInteger(pageNum);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.INVALID_PAGE_NUM.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
