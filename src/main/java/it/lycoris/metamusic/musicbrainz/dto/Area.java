package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a geographical area (e.g., country, region, city) in the MusicBrainz database.
 *
 * @param id       The MusicBrainz Identifier (MBID) for the area.
 * @param name     The standard name of the area.
 * @param sortName The name of the area intended for sorting purposes.
 * @param isoCodes A list of ISO 3166-1 codes associated with the area.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Area(
        String id,
        String name,
        @JsonProperty("sort-name")
        String sortName,
        @JsonProperty("iso-3166-1-codes")
        List<String> isoCodes
) {
}
