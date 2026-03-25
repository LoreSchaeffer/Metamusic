package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a release entity (e.g., an album, single, or EP) in the MusicBrainz database.
 *
 * @param id         The MusicBrainz Identifier (MBID) for the release.
 * @param statusId   The MBID representing the release status.
 * @param title      The title of the release.
 * @param status     The official status of the release (e.g., "Official", "Promotion").
 * @param date       The release date.
 * @param country    The country code where this release was issued.
 * @param trackCount The total number of tracks across all media in this release.
 * @param media      A list of {@link Media} objects comprising this release.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Release(
        String id,
        @JsonProperty("status-id")
        String statusId,
        String title,
        String status,
        String date,
        String country,
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
