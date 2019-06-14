package io.apeex.api.client.exception;

import io.apeex.api.client.ApeexApiError;

/**
 * An exception which can occur while invoking methods of the Apeex API.
 */
public class ApeexApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by Apeex API.
     */
    private ApeexApiError error;

    /**
     * Instantiates a new Apeex api exception.
     *
     * @param error an error response object
     */
    public ApeexApiException(ApeexApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new Apeex api exception.
     */
    public ApeexApiException() {
        super();
    }

    /**
     * Instantiates a new Apeex api exception.
     *
     * @param message the message
     */
    public ApeexApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Apeex api exception.
     *
     * @param cause the cause
     */
    public ApeexApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Apeex api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ApeexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from Apeex API, or null if no response object was returned (e.g. server returned 500).
     */
    public ApeexApiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
