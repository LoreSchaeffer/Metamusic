package it.lycoris.metamusic.common.exceptions;

/**
 * Base exception class for all music API related errors.
 * <p>
 * This exception can be used to wrap generic runtime errors that occur during API interactions,
 * such as network failures or serialization issues.
 */
public class MusicApiException extends RuntimeException {

    /**
     * Constructs a new {@code MusicApiException} with the specified detail message.
     *
     * @param message The detail message.
     */
    public MusicApiException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code MusicApiException} with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public MusicApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
