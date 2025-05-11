package umc8th.spring8th.apiPayload.exception.handler;

import umc8th.spring8th.apiPayload.code.BaseErrorCode;
import umc8th.spring8th.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
