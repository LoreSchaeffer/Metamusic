package it.lycoris.metamusic.itunes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Represents the top-level JSON response returned by the iTunes Search API.
 *
 * @param resultCount The number of results returned in this response.
 * @param results     The list of {@link ITunesTrack} objects representing the matched songs.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ITunesSearchResponse(
        Integer resultCount,
        List<ITunesTrack> results
) {
}
