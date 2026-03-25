package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Encapsulates the URLs for various thumbnail sizes of a cover art image.
 *
 * @param size1200 The URL for the 1200px thumbnail.
 * @param size500  The URL for the 500px thumbnail.
 * @param size250  The URL for the 250px thumbnail.
 * @param large    The URL for the large thumbnail.
 * @param small    The URL for the small thumbnail.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoverArtThumbnails(
        @JsonProperty("1200")
        String size1200,
        @JsonProperty("500")
        String size500,
        @JsonProperty("250")
        String size250,
        String large,
        String small
) {
}
