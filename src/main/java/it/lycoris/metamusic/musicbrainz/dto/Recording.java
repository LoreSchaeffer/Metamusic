package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a recording entity in the MusicBrainz database.
 *
 * @param id               The MusicBrainz Identifier (MBID) for the recording.
 * @param score            The relevance score assigned to this recording in the search results.
 * @param title            The title of the recording.
 * @param length           The duration of the recording in milliseconds.
 * @param video            True if this recording is flagged as a video.
 * @param firstReleaseDate The date when this recording was first released.
 * @param artistCredit     A list of {@link ArtistCredit} objects detailing the artists credited for this recording.
 * @param releases         A list of {@link Release} objects this recording appears on.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Recording(
        String id,
        Integer score,
        String title,
        Integer length,
        Boolean video,
        @JsonProperty("first-release-date")
        String firstReleaseDate,
        @JsonProperty("artist-credit")
        List<ArtistCredit>  artistCredit,
        List<Release> releases
) {
}
