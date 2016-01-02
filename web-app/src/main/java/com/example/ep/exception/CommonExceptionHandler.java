package com.example.ep.exception;

import com.example.core.domain.errorcode.Components;
import com.example.core.domain.errorcode.ErrorCodes;
import com.example.core.domain.errorcode.ReasonCodes;
import com.example.core.domain.errorcode.Subjects;
import com.example.ep.model.ApiError;
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
        logError(request, exception, exception.getMostSpecificCause().getMessage());
        return ApiError.builder()
                .errorCode("23232")
                .message("error while database operation")
                .status("error")
                .build();
    }

    @ExceptionHandler(value = DomainException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiError exception(HttpServletRequest request,
                              DomainException exception) {

        logError(request, exception);
        String error = ErrorCodes.getCode(Components.CP, exception.getFeatureCode(), exception.getReasonCode());
        ApiError.builder()
                .errorCode(error)
                .message(ErrorCodes.getMessage(error))
                .status("error")
                .build();

        return ApiError.builder()
                .errorCode("23232")
                .message("error while database operation")
                .status("error")
                .build();
    }

    private void logError(HttpServletRequest request, Exception exception) {
        logError(request, exception, exception.getMessage());
    }


    private void logError(HttpServletRequest request, Exception exception, String message) {
        log.warn("api error @{} {} : {}",
                request.getMethod(),
                request.getRequestURI(),
                message,
                exception);
    }

}
