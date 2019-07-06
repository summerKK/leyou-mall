package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    private ExceptionEnum exceptionEnum;

    public ExceptionResult(ExceptionEnum exceptionEnum) {
        status = exceptionEnum.getCode();
        message = exceptionEnum.getMsg();
        timestamp = System.currentTimeMillis();
    }
}
