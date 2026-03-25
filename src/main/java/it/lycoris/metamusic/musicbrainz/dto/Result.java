package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a recording entity in the MusicBrainz database.
 *
 * @param id               The MusicBrainz Identifier (MBID) for the recording.
 * @param score            The relevance score assigned to this recording in the search results.
 * @param artistCreditId   The MBID representing the specific artist credit for this recording.
 * @param title            The title of the recording.
 * @param length           The duration of the recording in milliseconds.
 * @param video            True if this recording is flagged as a video.
 * @param artistCredit     A list of {@link ArtistCredit} objects detailing the artists credited for this recording.
 * @param firstReleaseDate The date when this recording was first released.
 * @param releases         A list of {@link Release} objects this recording appears on.
 * @param isrcs            A list of International Standard Recording Codes (ISRCs) associated with this recording.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(
        String id,
        Integer score,
        @JsonProperty("artist-credit-id")
        String artistCreditId,
        String title,
        Long length,
        Boolean video,
        @JsonProperty("artist-credit")
        List<ArtistCredit> artistCredit,
        @JsonProperty("first-release-date")
        String firstReleaseDate,
        List<Release> releases,
        List<String> isrcs
) {
}
