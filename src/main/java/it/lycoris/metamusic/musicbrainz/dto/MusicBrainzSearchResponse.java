package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents the top-level response from the MusicBrainz Recording search API.
 *
 * @param created A timestamp indicating when this response was generated.
 * @param count   The total number of search results available on the server.
 * @param offset  The zero-based offset of this page of results.
 * @param results The list of {@link Recording} objects matched by the query.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicBrainzSearchResponse(
        String created,
        Integer count,
        Integer offset,
        @JsonProperty("recordings")
        List<Recording> results
) {
}
