package it.lycoris.metamusic.itunes;

import it.lycoris.metamusic.common.AbstractMusicClient;
import it.lycoris.metamusic.common.exceptions.MusicApiException;
import it.lycoris.metamusic.itunes.dto.ITunesSearchResponse;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;

/**
 * A client implementation for interacting with the iTunes Search API.
 * <p>
 * This class extends {@link AbstractMusicClient} to provide specific configuration
 * and search functionality for iTunes songs.
 */
public class ITunesClient extends AbstractMusicClient<ITunesSearchResponse> {
    /**
     * The base URL for the iTunes Search API.
     */
    private static final String BASE_URL = "https://itunes.apple.com/search";

    /**
     * Constructs a new {@code ITunesClient} with full configuration.
     *
     * @param userAgent         The User-Agent header value. Must not be null or blank.
     * @param connectionTimeout The connection timeout in seconds. Must be strictly positive.
     * @param objectMapper      The Jackson {@link ObjectMapper} instance. If null, a default instance is initialized.
     */
    public ITunesClient(String userAgent, int connectionTimeout, ObjectMapper objectMapper) {
        super(userAgent, connectionTimeout, objectMapper);
    }

    /**
     * Constructs a new {@code ITunesClient} with a default {@link ObjectMapper}.
     *
     * @param userAgent         The User-Agent header value.
     * @param connectionTimeout The connection timeout in seconds.
     */
    public ITunesClient(String userAgent, int connectionTimeout) {
        super(userAgent, connectionTimeout);
    }

    /**
     * Constructs a new {@code ITunesClient} with a default connection timeout of 10 seconds.
     *
     * @param userAgent    The User-Agent header value.
     * @param objectMapper The Jackson {@link ObjectMapper} instance.
     */
    public ITunesClient(String userAgent, ObjectMapper objectMapper) {
        super(userAgent, objectMapper);
    }

    /**
     * Constructs a new {@code ITunesClient} with a default connection timeout of 10 seconds
     * and a default {@link ObjectMapper}.
     *
     * @param userAgent The User-Agent header value.
     */
    public ITunesClient(String userAgent) {
        super(userAgent);
    }

    /**
     * Executes a search query against the iTunes API for songs matching the given term.
     *
     * @param query The text string to search for.
     * @return The {@link ITunesSearchResponse} containing the search results.
     * @throws MusicApiException if an error occurs during the API call or if an empty response is received.
     */
    @Override
    public ITunesSearchResponse search(String query) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = String.format("%s?term=%s&entity=song", BASE_URL, encodedQuery);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", this.userAgent)
                .GET()
                .build();

        return executeRequest(request, ITunesSearchResponse.class)
                .orElseThrow(() -> new MusicApiException("Empty response received from iTunes."));
    }
}
