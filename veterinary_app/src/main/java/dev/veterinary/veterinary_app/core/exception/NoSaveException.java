package dev.veterinary.veterinary_app.core.exception;


public class NoSaveException extends RuntimeException{
    public NoSaveException(Long id , Class<?> xclass) {
        super(id + " ID'li " + xclass.getSimpleName() + " zaten kayıtlı.");
    }
}
