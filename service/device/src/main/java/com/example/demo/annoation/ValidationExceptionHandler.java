package com.example.demo.annoation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenrunzheng
 * @since 2020/4/28 17:16
 */
@ControllerAdvice(annotations = RestController.class)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object processValidationError(MethodArgumentNotValidException e) {
        Object request = e.getBindingResult().getTarget();

        List<String> errors = e.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    }
                    return error.toString();
                })
                .collect(Collectors.toList());
        return errors;
    }
}
