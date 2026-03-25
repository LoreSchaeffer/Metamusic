package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a specific piece of media (e.g., a CD or digital media) within a release.
 *
 * @param id          The MusicBrainz Identifier (MBID) for the media.
 * @param position    The position of this media in the release (e.g., Disc 1).
 * @param format      The format of the media (e.g., "Digital Media", "CD").
 * @param trackCount  The total number of tracks on this specific media.
 * @param trackOffset The accumulated number of tracks from preceding media in the same release.
 * @param tracks      The list of {@link Track} objects representing the tracks on this media.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Media(
        String id,
        Integer position,
        String format,
        @JsonProperty("track")
        List<Track> tracks,
        @JsonProperty("track-count")
        Integer trackCount,
        @JsonProperty("track-offset")
        Integer trackOffset
) {
}
