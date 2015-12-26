package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mac on 11/29/15.
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiError exception(HttpServletRequest request,
                              DataIntegrityViolationException exception) {

        log.warn("api error @{} {} : {}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMostSpecificCause().getMessage(),
                exception);

        return ApiError.builder()
                .errorCode("23232")
                .message("error while database operation")
                .status("error")
                .build();
    }
}
