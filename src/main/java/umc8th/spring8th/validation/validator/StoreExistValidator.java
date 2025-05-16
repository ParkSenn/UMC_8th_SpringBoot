package umc8th.spring8th.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.service.StoreService.StoreValidationService;
import umc8th.spring8th.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreValidationService storeValidationService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {

        boolean isValid =  storeValidationService.isStoreExist(storeId);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
