package com.sparta.idg.libraryapp259.model.exceptions.advice;

import com.sparta.idg.libraryapp259.model.exceptions.AuthorNotFoundException;
import com.sparta.idg.libraryapp259.model.exceptions.AuthorNotFoundResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// or could use @RestController in which case we need the @ResponseBody below
@RestControllerAdvice
public class AuthorNotFoundAdvice {

   //@ResponseBody // returns response as JSON
   @ExceptionHandler(AuthorNotFoundException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AuthorNotFoundResponse> authorNotFoundHandler( //objects represents a full HTTP response
                                                                         AuthorNotFoundException e,
                                                                         HttpServletRequest request){
       AuthorNotFoundResponse response = new AuthorNotFoundResponse(e.getMessage(), 400, request.getRequestURL().toString());
       return ResponseEntity.badRequest().body(response);
   }
}
