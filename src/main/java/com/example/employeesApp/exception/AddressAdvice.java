package com.example.employeesApp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AddressAdvice {
    @ResponseBody
    @ExceptionHandler({AddressException.class})
    public Map<String,String> exceptionHandler(AddressException exception){
        Map<String ,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

}
