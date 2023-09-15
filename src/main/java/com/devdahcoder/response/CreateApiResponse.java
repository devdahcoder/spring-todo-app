package com.devdahcoder.response;

import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Represents the response structure for a creation (POST) request.
 */
public class CreateApiResponse<T> {

    /**
     * The status of the response, typically an HTTP status code and message.
     */
    private HttpStatus status;

    /**
     * The data returned in the response, which may vary in structure.
     */
    private Map<String, T> data;

    /**
     * Links to related resources or actions.
     */
    private Map<String, String> links;

    /**
     * Constructs a CreateApiResponse object.
     *
     * @param status The status of the response.
     * @param data   The data returned in the response.
     * @param links  Links to related resources or actions.
     */
    public CreateApiResponse(HttpStatus status, Map<String, T> data, Map<String, String> links) {

        this.status = status;
        this.data = data;
        this.links = links;

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
     * Gets the data returned in the response.
     *
     * @return The data returned in the response.
     */
    public Map<String, T> getData() {

        return data;

    }

    /**
     * Gets the links to related resources or actions.
     *
     * @return Links to related resources or actions.
     */
    public Map<String, String> getLinks() {

        return links;

    }

}
