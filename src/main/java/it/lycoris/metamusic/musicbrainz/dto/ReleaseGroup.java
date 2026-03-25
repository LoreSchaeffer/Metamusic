package it.lycoris.metamusic.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a release group (e.g., album, single, EP) in the MusicBrainz database.
 * A release group groups together different releases of the same logical entity.
 *
 * @param id               The MusicBrainz Identifier (MBID) for the release group.
 * @param typeId           The MBID representing the type of the release group.
 * @param primaryTypeId    The MBID representing the primary type of the release group.
 * @param title            The title of the release group.
 * @param primaryType      The primary type of the release group (e.g., "Album", "Single").
 * @param secondaryTypes   A list of secondary types for the release group (e.g., "Compilation", "Live").
 * @param secondaryTypeIds A list of MBIDs representing the secondary types.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ReleaseGroup(
        String id,
        @JsonProperty("type-id")
        String typeId,
        @JsonProperty("primary-type-id")
        String primaryTypeId,
        String title,
        @JsonProperty("primary-type")
        String primaryType,
        @JsonProperty("secondary-types")
        List<String> secondaryTypes,
        @JsonProperty("secondary-type-ids")
        List<String> secondaryTypeIds
) {
}
