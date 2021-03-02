package com.easymanager.easymanager.config.exeption;

public class NotFoundExeption extends RuntimeException{
    public NotFoundExeption() {
    }

    public NotFoundExeption(String message) {
        super(message);
    }
}
