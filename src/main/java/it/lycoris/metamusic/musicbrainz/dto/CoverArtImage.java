package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Represents a single cover art image within a Cover Art Archive response.
 *
 * @param approved   Whether this image has been approved by the community.
 * @param back       True if this image represents the back cover.
 * @param front      True if this image represents the front cover.
 * @param comment    A free-text comment describing the image.
 * @param edit       The ID of the edit that added this image.
 * @param id         The unique identifier for the image.
 * @param image      The URL to the full-size image.
 * @param thumbnails The various thumbnail sizes available for this image.
 * @param types      A list of types characterizing this image (e.g., "Front", "Back", "Booklet").
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CoverArtImage(
        Boolean approved,
        Boolean back,
        Boolean front,
        String comment,
        Long edit,
        Long id,
        String image,
        CoverArtThumbnails thumbnails,
        List<String> types
) {
}
