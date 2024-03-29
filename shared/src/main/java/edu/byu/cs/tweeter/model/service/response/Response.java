package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

/**
 * A base class for server responses.
 */
class Response {

    private final boolean success;
    private final String message;

    /**
     * Creates an instance with a null message.
     *
     * @param success the success message.
     */
    Response(boolean success) {
        this(success, null);
    }

    /**
     * Creates an instance.
     *
     * @param success the success indicator.
     * @param message the error message.
     */
    Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Indicates whether the response represents a successful result.
     *
     * @return the success indicator.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * The error message for unsuccessful results.
     *
     * @return an error message or null if the response indicates a successful result.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return success == response.success &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }
}
