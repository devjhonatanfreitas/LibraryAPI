package com.jhonatanfreitas.LibraryAPI.Book;

import javax.lang.model.type.ErrorType;

public class BookException extends RuntimeException {
    private final ErrorType errorType;

    public BookException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public enum ErrorType{
        NOT_FOUND,
        NOT_RENTED,
        ALREADY_RENTED
    }

    public BookException(String message, ErrorType errorType){

        super(message);
        this.errorType = errorType;

    }

}
