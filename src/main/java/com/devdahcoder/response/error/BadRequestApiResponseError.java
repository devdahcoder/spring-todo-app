package com.devdahcoder.response.error;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;


/**
 * Represents the response structure for a failed creation (POST) request.
 */
public class BadRequestApiResponseError<T> {

    /**
     * The status of the response, typically an HTTP error status code and message.
     */
    private final HttpStatus status;

    /**
     * A human-readable error message explaining the reason for the failure.
     */
    private final String error;

    /**
     * A machine-readable error code to categorize the type of error.
     */
    private final int errorCode;

    /**
     * Additional details about the error, such as which field caused the issue.
     */
    private final List<T> errorDetails;

    /**
     * Constructs a FailedCreateApiResponse object.
     *
     * @param status       The status of the response (e.g., "400 Bad Request").
     * @param error        A human-readable error message.
     * @param errorCode    A machine-readable error code.
     * @param errorDetails Additional details about the error.
     */
    public BadRequestApiResponseError(HttpStatus status, String error, int errorCode, List<T> errorDetails) {

        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;

    }

    /**
     * Gets the status of the response.
     *
     * @return The status of the response.
     */
    public HttpStatus getStatus() {

        return status;

    }

    /**
     * Gets the human-readable error message.
     *
     * @return A human-readable error message.
     */
    public String getError() {

        return error;

    }

    /**
     * Gets the machine-readable error code.
     *
     * @return A machine-readable error code.
     */
    public int getErrorCode() {

        return errorCode;

    }

    /**
     * Gets additional details about the error.
     *
     * @return Additional details about the error.
     */
    public List<T> getErrorDetails() {

        return errorDetails;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof BadRequestApiResponseError<?> that)) return false;

        return getErrorCode() == that.getErrorCode() && getStatus() == that.getStatus() && Objects.equals(getError(), that.getError()) && Objects.equals(getErrorDetails(), that.getErrorDetails());

    }

    @Override
    public int hashCode() {

        return Objects.hash(getStatus(), getError(), getErrorCode(), getErrorDetails());

    }

    @Override
    public String toString() {

        return "ApiBadRequestExceptionModel{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", errorCode=" + errorCode +
                ", errorDetails=" + errorDetails +
                '}';

    }

}
