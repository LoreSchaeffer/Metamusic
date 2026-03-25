package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an individual track on a specific media within a release.
 *
 * @param id     The MusicBrainz Identifier (MBID) for the track.
 * @param number The track number, typically formatted as a string (e.g., "1", "A1").
 * @param title  The title of the track.
 * @param length The duration of the track in milliseconds.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Track(
        String id,
        String number,
        String title,
        Integer length
) {
}
