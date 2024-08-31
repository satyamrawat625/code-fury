package com.ecommerce.exception;

public class QtyNotValidException extends Exception {
    public QtyNotValidException(String message) {
        super(message);
    }

    public QtyNotValidException() {
        super();
    }

    public QtyNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public QtyNotValidException(Throwable cause) {
        super(cause);
    }

    protected QtyNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
