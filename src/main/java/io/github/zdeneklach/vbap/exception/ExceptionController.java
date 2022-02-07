package io.github.zdeneklach.vbap.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception ex) {
        log.error(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(HttpServletRequest request, Exception ex) {
        log.error(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(HttpServletRequest request, Exception ex) {
        log.error(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
    }
    @ExceptionHandler(value = DbException.class)
    public ResponseEntity<String> handleDbException(HttpServletRequest request, Exception ex) {
        log.error(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(String.format("%s threw an exception: %s", request.getRequestURL(), ex.getMessage()));
    }
}
