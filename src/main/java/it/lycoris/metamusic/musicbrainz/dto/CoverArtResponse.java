package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Represents the top-level response from the Cover Art Archive API for a specific release.
 *
 * @param images  A list of {@link CoverArtImage} objects associated with the release.
 * @param release The URL of the MusicBrainz release entity this cover art belongs to.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoverArtResponse(
        List<CoverArtImage> images,
        String release
) {
}
