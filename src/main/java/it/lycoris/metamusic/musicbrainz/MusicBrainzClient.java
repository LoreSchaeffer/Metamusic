package it.lycoris.metamusic.musicbrainz;

import it.lycoris.metamusic.common.AbstractMusicClient;
import it.lycoris.metamusic.common.exceptions.MusicApiException;
import it.lycoris.metamusic.musicbrainz.dto.CoverArtResponse;
import it.lycoris.metamusic.musicbrainz.dto.MusicBrainzSearchResponse;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * A client implementation for interacting with the MusicBrainz APIs, including searching for recordings
 * and fetching cover art from the Cover Art Archive.
 * <p>
 * This class extends {@link AbstractMusicClient} to provide configuration
 * and functionality tailored for the MusicBrainz WS/2 endpoints.
 */
public class MusicBrainzClient extends AbstractMusicClient<MusicBrainzSearchResponse> {
    /**
     * The base URL for the MusicBrainz Recording search endpoint.
     */
    private static final String BASE_URL = "https://musicbrainz.org/ws/2/recording/";
    /**
     * The base URL for the Cover Art Archive API.
     */
    private static final String COVER_ART_BASE_URL = "https://coverartarchive.org/release/";

    /**
     * Constructs a new {@code MusicBrainzClient} with full configuration.
     *
     * @param userAgent         The user agent string. MusicBrainz requires a descriptive User-Agent
     *                          e.g., "MyClient/1.0 ( me@example.com )"
     * @param connectionTimeout The connection timeout in seconds. Must be strictly positive.
     * @param objectMapper      The Jackson {@link ObjectMapper} instance. If null, a default instance is initialized.
     */
    public MusicBrainzClient(String userAgent, int connectionTimeout, ObjectMapper objectMapper) {
        super(userAgent, connectionTimeout, objectMapper);
    }

    /**
     * Constructs a new {@code MusicBrainzClient} with a default {@link ObjectMapper}.
     *
     * @param userAgent         The user agent string. MusicBrainz requires a descriptive User-Agent
     *                          e.g., "MyClient/1.0 ( me@example.com )"
     * @param connectionTimeout The connection timeout in seconds.
     */
    public MusicBrainzClient(String userAgent, int connectionTimeout) {
        super(userAgent, connectionTimeout);
    }

    /**
     * Constructs a new {@code MusicBrainzClient} with a default connection timeout of 10 seconds.
     *
     * @param userAgent    The user agent string. MusicBrainz requires a descriptive User-Agent
     *                     e.g., "MyClient/1.0 ( me@example.com )"
     * @param objectMapper The Jackson {@link ObjectMapper} instance.
     */
    public MusicBrainzClient(String userAgent, ObjectMapper objectMapper) {
        super(userAgent, objectMapper);
    }

    /**
     * Constructs a new {@code MusicBrainzClient} with a default connection timeout of 10 seconds
     * and a default {@link ObjectMapper}.
     *
     * @param userAgent The user agent string. MusicBrainz requires a descriptive User-Agent
     *                  e.g., "MyClient/1.0 ( me@example.com )"
     */
    public MusicBrainzClient(String userAgent) {
        super(userAgent);
    }

    /**
     * Executes a search query for recordings against the MusicBrainz API with pagination parameters.
     *
     * @param query  The Lucene-formatted query string.
     * @param limit  The maximum number of results to return.
     * @param offset The zero-based offset into the full result set.
     * @return The {@link MusicBrainzSearchResponse} containing the search results.
     * @throws MusicApiException if an error occurs during the API call or if an empty response is received.
     */
    public MusicBrainzSearchResponse search(String query, int limit, int offset) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = String.format("%s?query=%s&fmt=json&limit=%d&offset=%d", BASE_URL, encodedQuery, limit, offset);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", this.userAgent)
                .GET()
                .build();

        return executeRequest(request, MusicBrainzSearchResponse.class)
                .orElseThrow(() -> new MusicApiException("Empty response received from MusicBrainz."));
    }

    /**
     * Executes a search query for recordings against the MusicBrainz API with default pagination
     * (limit of 50, offset of 0).
     *
     * @param query The Lucene-formatted query string.
     * @return The {@link MusicBrainzSearchResponse} containing the search results.
     */
    @Override
    public MusicBrainzSearchResponse search(String query) {
        return search(query, 50, 0);
    }

    /**
     * Fetches cover art details for a specific release from the Cover Art Archive.
     *
     * @param releaseId The MBID (MusicBrainz Identifier) of the release.
     * @return An {@link Optional} containing a {@link CoverArtResponse} if the art is found, or empty otherwise.
     */
    public Optional<CoverArtResponse> getCoverArt(String releaseId) {
        String url = COVER_ART_BASE_URL + releaseId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", this.userAgent)
                .GET()
                .build();

        return executeRequest(request, CoverArtResponse.class);
    }
}
