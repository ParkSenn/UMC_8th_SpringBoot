package umc8th.spring8th.apiPayload.exception.handler;

import umc8th.spring8th.apiPayload.code.BaseErrorCode;
import umc8th.spring8th.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
