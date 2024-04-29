package com.codespark.springbootelasticsearch.common.errors;

public class NullValueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NullValueException(String message) {
        super(message);
    }

    public NullValueException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
