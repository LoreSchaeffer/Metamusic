package it.lycoris.metamusic.itunes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an individual track (song) within an iTunes Search API response.
 *
 * @param wrapperType          The wrapper type (e.g., "track").
 * @param kind                 The kind of media (e.g., "song").
 * @param artistId             The unique identifier for the artist.
 * @param collectionId         The unique identifier for the collection (album).
 * @param trackId              The unique identifier for the track.
 * @param artistName           The name of the artist.
 * @param collectionName       The name of the collection (album).
 * @param trackName            The name of the track.
 * @param collectionArtistName The name of the artist associated with the collection.
 * @param artistViewUrl        A URL to view the artist on Apple Music.
 * @param collectionViewUrl    A URL to view the collection on Apple Music.
 * @param trackViewUrl         A URL to view the track on Apple Music.
 * @param previewUrl           A URL for a 30-second audio preview of the track.
 * @param artworkUrl30         A URL for the track artwork (30x30 pixels).
 * @param artworkUrl60         A URL for the track artwork (60x60 pixels).
 * @param artworkUrl100        A URL for the track artwork (100x100 pixels).
 * @param releaseDate          The release date of the track in ISO 8601 format.
 * @param discCount            The total number of discs in the collection.
 * @param discNumber           The disc number this track is on.
 * @param trackCount           The total number of tracks in the collection.
 * @param trackNumber          The number of this track within the collection.
 * @param trackTimeMillis      The duration of the track in milliseconds.
 * @param primaryGenreName     The primary genre name associated with the track.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ITunesTrack(
        String wrapperType,
        String kind,
        Long artistId,
        Long collectionId,
        Long trackId,
        String artistName,
        String collectionName,
        String trackName,
        String collectionArtistName,
        String artistViewUrl,
        String collectionViewUrl,
        String trackViewUrl,
        String previewUrl,
        String artworkUrl30,
        String artworkUrl60,
        String artworkUrl100,
        String releaseDate,
        Integer discCount,
        Integer discNumber,
        Integer trackCount,
        Integer trackNumber,
        Long trackTimeMillis,
        String primaryGenreName
) {
}
