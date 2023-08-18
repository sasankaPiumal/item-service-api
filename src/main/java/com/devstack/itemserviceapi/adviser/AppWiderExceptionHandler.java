package com.devstack.itemserviceapi.adviser;

import com.devstack.itemserviceapi.exception.ClassNotFoundException;
import com.devstack.itemserviceapi.exception.DuplicateEntryException;
import com.devstack.itemserviceapi.exception.EntryNotFoundException;
import com.devstack.itemserviceapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiderExceptionHandler {

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, e.getMessage(), e), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<StandardResponse> handleClassNotFoundException(ClassNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, e.getMessage(), e), HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<StandardResponse> handleDuplicateEntryException(DuplicateEntryException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, e.getMessage(), e), HttpStatus.CONFLICT
        );
    }

}
