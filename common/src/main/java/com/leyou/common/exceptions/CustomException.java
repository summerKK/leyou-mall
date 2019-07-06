package com.leyou.common.exceptions;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private ExceptionEnum exceptionEnum;
}
