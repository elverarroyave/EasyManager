package com.easymanager.easymanager.product.io.web.request;

public class ProductResponse {

    private String message;

    public String getMessage(){return message;}

    public void setMessage(String message){this.message = message;}

    public ProductResponse(String message){
        this.message = message;
    }

    public ProductResponse(){}
}
