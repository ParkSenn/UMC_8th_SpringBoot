package umc8th.spring8th.converter;

import umc8th.spring8th.web.dto.Temp.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDTO toTempTestDTO() {

        return TempResponse.TempTestDTO.builder()
                .testString("This is test!!!")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
