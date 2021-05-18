package com.easymanager.easymanager.config;

import com.easymanager.easymanager.config.exeption.BadRequestExeption;
import com.easymanager.easymanager.config.exeption.InsufficientStock;
import com.easymanager.easymanager.config.exeption.NotFoundExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExceptionController.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageResponse> handleMethodArgumentNotValidMethod(MethodArgumentNotValidException ex){
        LOGGER.error("EL ATRIBUTO \"" + ex.getBindingResult().getFieldError().getField().toUpperCase() + "\" " +
                ex.getBindingResult().getFieldError().getDefaultMessage().toUpperCase());

        return new ResponseEntity<>(new MessageResponse(
                "EL ATRIBUTO \"" + ex.getBindingResult().getFieldError().getField().toUpperCase() + "\" " +
                        ex.getBindingResult().getFieldError().getDefaultMessage().toUpperCase()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<?> notFoundException(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadRequestExeption.class)
    public ResponseEntity<?> badRequestException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InsufficientStock.class)
    public ResponseEntity<?> insufficientStock(Exception e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
