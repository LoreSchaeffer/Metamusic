package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an artist entity from the MusicBrainz API.
 *
 * @param id             The MusicBrainz Identifier (MBID) for the artist.
 * @param name           The standard name of the artist.
 * @param sortName       The name of the artist intended for sorting purposes.
 * @param disambiguation A string intended to help distinguish artists with the same name.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Artist(
        String id,
        String name,
        @JsonProperty("sort-name")
        String sortName,
        String disambiguation
) {
}
