package umc8th.spring8th.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc8th.spring8th.apiPayload.code.BaseErrorCode;
import umc8th.spring8th.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "MEMBER4003", "유효하지 않은 토큰입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER4004", "패스워드가 불일치합니다."),
    DUPLICATE_JOIN_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER4005", "해당 이메일로 이미 가입된 사용자가 존재합니다."),

    // 선호 음식 관련 에러
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY4001", "해당되는 선호 음식이 없습니다"),

    // Store 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당되는 가게가 없습니다."),

    // Mission 관련 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당되는 미션이 없습니다."),

    // MemberMission 관련 에러
    CHALLENGING_MEMBER_MISSION_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_MISSION4001", "이미 도전 중인 미션입니다."),

    // Paging 관련 에러
    INVALID_PAGE_NUM(HttpStatus.BAD_REQUEST, "Page4001", "page는 1 이상의 정수여야 합니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
