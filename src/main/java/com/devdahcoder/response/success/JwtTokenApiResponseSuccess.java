package com.devdahcoder.response.success;

/**
 * Represents the response structure for a successful JWT token generation request.
 */
public class JwtTokenApiResponseSuccess {

    /**
     * The status of the response, typically an HTTP status code and message.
     */
    private final String status;

    /**
     * A human-readable message indicating the success of JWT token generation.
     */
    private final String message;

    /**
     * The generated JWT token.
     */
    private final String jwtToken;

    /**
     * Constructs a JwtTokenResponse object.
     *
     * @param status   The status of the response.
     * @param message  A human-readable message indicating success.
     * @param jwtToken The generated JWT token.
     */
    public JwtTokenApiResponseSuccess(String status, String message, String jwtToken) {

        this.status = status;
        this.message = message;
        this.jwtToken = jwtToken;

    }

    /**
     * Gets the status of the response.
     *
     * @return The status of the response.
     */
    public String getStatus() {

        return status;

    }

    /**
     * Gets the human-readable success message.
     *
     * @return A human-readable message indicating success.
     */
    public String getMessage() {

        return message;

    }

    /**
     * Gets the generated JWT token.
     *
     * @return The generated JWT token.
     */
    public String getJwtToken() {

        return jwtToken;

    }

}


