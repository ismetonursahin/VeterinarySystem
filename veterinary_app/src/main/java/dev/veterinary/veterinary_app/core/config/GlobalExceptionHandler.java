package dev.veterinary.veterinary_app.core.config;

import dev.veterinary.veterinary_app.core.exception.NoExistenceException;
import dev.veterinary.veterinary_app.core.exception.NoSaveException;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()) , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)   // onemli sor
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){

        List<String > validationErrorList =  e.getBindingResult().getFieldErrors().stream()  // validation hatalarını bunun içersine attı
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoExistenceException.class)
    public ResponseEntity<Result> handleExistenceException(NoExistenceException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()) , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSaveException.class)
    public ResponseEntity<Result> handleNoSaveException(NoSaveException e){
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()) , HttpStatus.NOT_FOUND);
    }


}
