package com.christian.mendez.quasarfireoperation.exception;

import com.christian.mendez.quasarfireoperation.util.ConstantsEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SatelliteNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleSatelliteNotFoundException() {
        log.error("[handleSatelliteNotFoundException] [FINEX]");
        return ExceptionResponse.builder().mensaje(ConstantsEnum.SATELLITE_NOT_FOUND.toString()).build();
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleLocationNotFoundException() {
        log.error("[handleLocationNotFoundException] [FINEX]");
        return ExceptionResponse.builder().mensaje(ConstantsEnum.LOCATION_NOT_FOUND.toString()).build();
    }

    @ExceptionHandler(CircleNotIntersectException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleCircleNotIntersectException() {
        log.error("[handleCircleNotIntersectException] [FINEX]");
        return ExceptionResponse.builder().mensaje(ConstantsEnum.CIRCLE_NOT_INTERSECT.toString()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
