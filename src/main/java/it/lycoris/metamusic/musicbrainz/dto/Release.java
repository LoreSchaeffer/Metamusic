package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a release entity (e.g., an album, single, or EP) in the MusicBrainz database.
 *
 * @param id             The MusicBrainz Identifier (MBID) for the release.
 * @param statusId       The MBID representing the release status.
 * @param artistCreditId The MBID representing the specific artist credit for this release.
 * @param count          The number of times this release matches the search criteria or the total count.
 * @param title          The title of the release.
 * @param status         The official status of the release (e.g., "Official", "Promotion").
 * @param artistCredit   A list of {@link ArtistCredit} objects detailing the artists credited for this release.
 * @param releaseGroup   The {@link ReleaseGroup} that this release belongs to.
 * @param date           The release date.
 * @param country        The country code where this release was issued.
 * @param releaseEvents  A list of {@link ReleaseEvent} objects detailing specific release dates and areas.
 * @param trackCount     The total number of tracks across all media in this release.
 * @param media          A list of {@link Media} objects comprising this release.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Release(
        String id,
        @JsonProperty("status-id")
        String statusId,
        @JsonProperty("artist-credit-id")
        String artistCreditId,
        Integer count,
        String title,
        String status,
        @JsonProperty("artist-credit")
        List<ArtistCredit> artistCredit,
        @JsonProperty("release-group")
        ReleaseGroup releaseGroup,
        String date,
        String country,
        @JsonProperty("release-events")
        List<ReleaseEvent> releaseEvents,
        @JsonProperty("track-count")
        Integer trackCount,
        List<Media> media
) {
    /**
     * A formatting template for the primary front cover art URL via the Cover Art Archive.
     */
    public static final String COVER_ART_URL = "https://coverartarchive.org/release/%s/front";

    /**
     * Generates the URL for the front cover art of this release.
     *
     * @return The absolute URL to the front cover image.
     */
    public String cover() {
        return String.format(COVER_ART_URL, id);
    }
}
