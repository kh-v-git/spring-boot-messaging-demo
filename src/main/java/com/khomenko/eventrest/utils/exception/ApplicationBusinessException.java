/*
 * Do not reproduce without permission in writing.
 * Copyright (c) 2023.
 */
package com.khomenko.eventrest.utils.exception;

public class ApplicationBusinessException extends RuntimeException {
    public ApplicationBusinessException() {
        super();
    }

    public ApplicationBusinessException(String message) {
        super(message);
    }

    public ApplicationBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationBusinessException(Throwable cause) {
        super(cause);
    }
}
