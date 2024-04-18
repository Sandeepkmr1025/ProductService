package com.sandeep.productservice.advices;

import com.sandeep.productservice.dtos.ArithmeticExceptionDto;
import com.sandeep.productservice.dtos.ArrayIndexOutOfBoundExceptionDto;
import com.sandeep.productservice.dtos.ExceptionDto;
import com.sandeep.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException()
    {
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("Something went wrong");
        dto.setDetail("Some random detail");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundExceptionDto> handleAIOBException()
    {
        ArrayIndexOutOfBoundExceptionDto dto = new ArrayIndexOutOfBoundExceptionDto();
        dto.setMessage("Array Index Out Of Bound Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException exception)
    {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Invalid productId passed, please retry with valid productId");
        dto.setProductId(exception.getProductId());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
