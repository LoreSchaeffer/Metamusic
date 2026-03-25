package it.lycoris.metamusic.common;

import it.lycoris.metamusic.common.exceptions.MusicApiClientExcpetion;
import it.lycoris.metamusic.common.exceptions.MusicApiException;
import it.lycoris.metamusic.common.exceptions.MusicApiServerException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

/**
 * Abstract base client providing core HTTP communication and JSON deserialization
 * functionalities for interacting with various external music APIs.
 * <p>
 * This class encapsulates the boilerplate code required to configure the HTTP client,
 * send requests, handle standard HTTP status codes, and map the JSON responses to domain objects.
 * It strictly relies on the native Java {@link HttpClient} and Jackson's {@link ObjectMapper}.
 *
 * @param <T> The expected return type of the primary search operation implemented by subclasses.
 */
public abstract class AbstractMusicClient<T> {
    /**
     * The native HTTP client used to execute requests. Configured to be immutable after construction.
     */
    protected final HttpClient httpClient;
    /**
     * The Jackson object mapper used for JSON deserialization.
     */
    protected final ObjectMapper objectMapper;
    /**
     * The user agent string sent in the HTTP headers to identify the client to the provider.
     */
    protected final String userAgent;

    /**
     * Constructs a new {@code AbstractMusicClient} with full configuration.
     *
     * @param userAgent         The User-Agent header value. Must not be null or blank.
     * @param connectionTimeout The connection timeout in seconds. Must be strictly positive.
     * @param objectMapper      The Jackson {@link ObjectMapper} instance. If null, a default instance is initialized.
     * @throws IllegalArgumentException if the userAgent is null/blank or the connectionTimeout is not positive.
     */
    public AbstractMusicClient(String userAgent, int connectionTimeout, ObjectMapper objectMapper) {
        if (userAgent == null || userAgent.isBlank()) throw new IllegalArgumentException("User-Agent cannot be null or empty");
        if (connectionTimeout <= 0) throw new IllegalArgumentException("Connection timeout must be positive");

        this.objectMapper = objectMapper != null ? objectMapper : new ObjectMapper();
        this.userAgent = userAgent;

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(connectionTimeout))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    /**
     * Constructs a new {@code AbstractMusicClient} with a default {@link ObjectMapper}.
     *
     * @param userAgent         The User-Agent header value.
     * @param connectionTimeout The connection timeout in seconds.
     */
    public AbstractMusicClient(String userAgent, int connectionTimeout) {
        this(userAgent, connectionTimeout, null);
    }

    /**
     * Constructs a new {@code AbstractMusicClient} with a default connection timeout of 10 seconds.
     *
     * @param userAgent    The User-Agent header value.
     * @param objectMapper The Jackson {@link ObjectMapper} instance.
     */
    public AbstractMusicClient(String userAgent, ObjectMapper objectMapper) {
        this(userAgent, 10, objectMapper);
    }

    /**
     * Constructs a new {@code AbstractMusicClient} with a default connection timeout of 10 seconds
     * and a default {@link ObjectMapper}.
     *
     * @param userAgent The User-Agent header value.
     */
    public AbstractMusicClient(String userAgent) {
        this(userAgent, 10, null);
    }

    /**
     * Executes a generic search query against the target music API.
     * <p>
     * Subclasses must implement this method to provide the specific endpoint routing,
     * query parameter encoding, and custom logic for their respective providers.
     *
     * @param query The text string to search for (e.g., an artist name or track title).
     * @return The domain-specific search response of type {@code T}.
     */
    public abstract T search(String query);

    /**
     * Executes the given HTTP request synchronously and deserializes the JSON response body.
     * <p>
     * This method handles the underlying I/O operations and evaluates the HTTP status code.
     * It returns an {@link Optional} containing the mapped object for successful responses (2xx),
     * an empty {@code Optional} for not found errors (404), and throws specific domain exceptions
     * for other client (4xx) or server (5xx) errors.
     *
     * @param request      The {@link HttpRequest} to be executed.
     * @param responseType The target {@link Class} representing the expected JSON structure.
     * @param <E>          The type of the expected response object.
     * @return An {@link Optional} containing the deserialized response, or empty if a 404 is returned.
     * @throws MusicApiClientExcpetion if the server returns a 4xx status code (other than 404).
     * @throws MusicApiServerException if the server returns a 5xx status code.
     * @throws MusicApiException       if a network error occurs or the thread is interrupted.
     */
    protected <E> Optional<E> executeRequest(HttpRequest request, Class<E> responseType) {
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode >= 200 && statusCode < 300) {
                return Optional.of(objectMapper.readValue(response.body(), responseType));
            } else if (statusCode == 404) {
                return Optional.empty();
            } else if (statusCode >= 400 && statusCode < 500) {
                throw new MusicApiClientExcpetion("Client error during API call", statusCode, response.body());
            } else {
                throw new MusicApiServerException("Server error during API call", statusCode, response.body());
            }
        } catch (IOException e) {
            throw new MusicApiException("Network error while communicating with API", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new MusicApiException("Thread was interrupted during API call", e);
        }
    }
}
