package bg.sofia.uni.fmi.tbb.web;

import bg.sofia.uni.fmi.tbb.exception.InvalidEntityException;
import bg.sofia.uni.fmi.tbb.exception.NonexistingEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice("bg.sofia.uni.fmi.tbb")
@Slf4j
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handle(NonexistingEntityException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({InvalidEntityException.class,
            ConstraintViolationException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<String> handle(Exception ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(AccessDeniedException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
