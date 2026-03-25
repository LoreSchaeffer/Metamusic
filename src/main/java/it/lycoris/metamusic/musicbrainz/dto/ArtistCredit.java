package it.lycoris.metamusic.musicbrainz.dto;

/**
 * Represents a credit attribution for an artist on a recording or release in the MusicBrainz API.
 *
 * @param joinphrase A string used to join this artist's name with the next artist's name (e.g., " feat. ").
 * @param name       The name of the artist as credited on this specific recording or release.
 * @param artist     The underlying {@link Artist} entity associated with this credit.
 */
public record ArtistCredit(
        String joinphrase,
        String name,
        Artist artist
) {
}
