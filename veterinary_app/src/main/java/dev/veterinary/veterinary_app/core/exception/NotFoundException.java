package dev.veterinary.veterinary_app.core.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

}
