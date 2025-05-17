package umc8th.spring8th.web.dto.MemberMission;

import lombok.Getter;
import umc8th.spring8th.validation.annotation.ExistMember;

public class MemberMissionRequestDTO {

    @Getter
    @ExistChallengingMemberMission
    public static class NewChallengingMemberMissionDTO {
        @ExistMember
        Long memberId;

        @ExistMission
        Long missionId;
    }
}
