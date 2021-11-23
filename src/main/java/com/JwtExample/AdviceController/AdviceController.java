package com.JwtExample.AdviceController;

import com.JwtExample.CustomException.BusinessException;
import com.JwtExample.CustomException.EmptyInputException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {
        return new ResponseEntity<String>("Input Fields Are Empty Please Provide Correct Value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException businessException) {
        return new ResponseEntity<String>("Something went wrong in Service layer while fetching user :-", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<String>("No Value Present At DB According To your Request,Please Try With Another Request.", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return new ResponseEntity<String>("Constraint Violation Exception ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<String>("You Don't Have Correct Right To Access, Please Send Some Id To Be Searched.", HttpStatus.BAD_REQUEST);
    }
/*

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity<String>("Something is Wrong In Email ,Please Provide Correct Email..", HttpStatus.BAD_GATEWAY);
    }
*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<String>("Something is Wrong Here.", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }
}