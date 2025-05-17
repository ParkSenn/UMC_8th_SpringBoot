package umc8th.spring8th.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.service.MemberMissionService.MemberMissionValidationService;
import umc8th.spring8th.validation.annotation.ExistChallengingMemberMission;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionRequestDTO;

@Component
@RequiredArgsConstructor
public class ChallengingMemberMissionExistValidator implements ConstraintValidator<ExistChallengingMemberMission, MemberMissionRequestDTO.NewChallengingMemberMissionDTO> {

    private final MemberMissionValidationService memberMissionValidationService;

    @Override
    public void initialize(ExistChallengingMemberMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberMissionRequestDTO.NewChallengingMemberMissionDTO dto, ConstraintValidatorContext context) {

        boolean isValid =!(memberMissionValidationService.alreadyChallengingMission(dto));

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.CHALLENGING_MEMBER_MISSION_ALREADY_EXIST.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}
