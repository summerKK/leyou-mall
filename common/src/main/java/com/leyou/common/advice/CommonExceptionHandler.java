package com.leyou.common.advice;

import com.leyou.common.exceptions.CustomException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResult> handler(CustomException e) {
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
