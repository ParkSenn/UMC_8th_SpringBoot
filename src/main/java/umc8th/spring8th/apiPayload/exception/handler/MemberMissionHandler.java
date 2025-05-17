package umc8th.spring8th.apiPayload.exception.handler;

import umc8th.spring8th.apiPayload.code.BaseErrorCode;
import umc8th.spring8th.apiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {

    public MemberMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
