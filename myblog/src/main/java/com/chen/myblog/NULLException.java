package com.chen.myblog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NULLException extends RuntimeException {
    public NULLException() {
    }

    public NULLException(String message) {
        super(message);
    }

    public NULLException(String message, Throwable cause) {
        super(message, cause);
    }

    public NULLException(Throwable cause) {
        super(cause);
    }

    public NULLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
