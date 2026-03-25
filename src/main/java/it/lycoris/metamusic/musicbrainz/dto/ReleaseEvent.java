package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a release event for a specific release in the MusicBrainz database.
 *
 * @param date The date when the release event occurred.
 * @param area The {@link Area} where the release event took place.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ReleaseEvent(
        String date,
        Area area
) {
}
