package it.lycoris.metamusic.common.exceptions;

/**
 * Exception thrown when a music API returns a 5xx server error HTTP status code.
 * <p>
 * This exception encapsulates the specific HTTP status code and the raw response body
 * to aid in debugging and error handling.
 */
public class MusicApiServerException extends MusicApiException {
    /**
     * The HTTP status code returned by the API.
     */
    private final int statusCode;
    /**
     * The raw response body returned by the API.
     */
    private final String responseBody;

    /**
     * Constructs a new {@code MusicApiServerException} with the supplied details.
     *
     * @param message      The detail message.
     * @param statusCode   The HTTP status code (e.g., 500, 502, 503).
     * @param responseBody The raw response body from the server.
     */
    public MusicApiServerException(String message, int statusCode, String responseBody) {
        super(message + " (HTTP " + statusCode + ")");
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    /**
     * Retrieves the HTTP status code.
     *
     * @return The HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Retrieves the raw response body.
     *
     * @return The response body string.
     */
    public String getResponseBody() {
        return responseBody;
    }
}