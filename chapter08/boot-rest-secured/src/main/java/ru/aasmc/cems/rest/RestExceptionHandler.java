package ru.aasmc.cems.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aasmc.cems.rest.person.PersonsController;
import ru.aasmc.cems.rest.person.PersonsException;

@ControllerAdvice(basePackageClasses = PersonsController.class)
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        if (e instanceof PersonsException) {
            PersonsException pe = (PersonsException) e;
            return new ResponseEntity<>(pe.errorMessage(), pe.getStatus());
        } else if (e instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>("Another entity with the same identity exists", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Unexpected Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
