package com.easymanager.easymanager.config.exeption;

public class InsufficientStock extends RuntimeException{
    public InsufficientStock(){

    }
    public InsufficientStock(String message){
        super(message);
    }
}
