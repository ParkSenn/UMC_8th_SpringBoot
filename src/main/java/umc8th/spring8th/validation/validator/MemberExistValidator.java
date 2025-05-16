package umc8th.spring8th.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.service.MemberService.MemberValidationService;
import umc8th.spring8th.validation.annotation.ExistMember;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMember, Long> {

    private final MemberValidationService memberValidationService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {

        boolean isValid =  memberValidationService.isMemberExist(memberId);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
