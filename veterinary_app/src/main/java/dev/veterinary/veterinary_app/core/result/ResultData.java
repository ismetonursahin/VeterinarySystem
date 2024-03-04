package dev.veterinary.veterinary_app.core.result;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.naming.spi.DirStateFactory;

@Getter

public class ResultData<T> extends Result{

    private T data;

    public ResultData(boolean status, String message, String code,T data) {
        super(status, message, code);
        this.data = data;
    }
}
