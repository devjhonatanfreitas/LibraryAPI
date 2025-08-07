package com.jhonatanfreitas.LibraryAPI.Book;

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
        ALREADY_RENTED,
        INVALID_DATA,
        ALREADY_EXISTS
    }

    public BookException(String message, ErrorType errorType){

        super(message);
        this.errorType = errorType;

    }

}
