package umc8th.spring8th.web.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMyPageInfoDTO {
        private Long id;
        private String name;
        private String email;
        private String phoneNum;
        private Integer point;
    }
}
