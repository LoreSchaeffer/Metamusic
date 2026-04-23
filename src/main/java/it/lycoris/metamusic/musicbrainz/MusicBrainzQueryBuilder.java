package it.lycoris.metamusic.musicbrainz;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder class to construct Apache Lucene queries for the MusicBrainz API.
 * This class ensures that queries are strictly bound to specific fields,
 * greatly improving the relevance of search results compared to raw text searches.
 */
public class MusicBrainzQueryBuilder {
    private final List<String> queryConditions = new ArrayList<>();

    /**
     * Adds a condition to search within the recording title.
     *
     * @param recordingName The title of the recording/song.
     * @return The current builder instance.
     */
    public MusicBrainzQueryBuilder withRecordingName(String recordingName) {
        return appendCondition("recording", recordingName);
    }

    /**
     * Adds a condition to search within the artist's primary name.
     *
     * @param artistName The name of the artist.
     * @return The current builder instance.
     */
    public MusicBrainzQueryBuilder withArtistName(String artistName) {
        return appendCondition("artist", artistName);
    }

    /**
     * Adds a condition to search within the artist's aliases.
     * This is extremely useful for Japanese artists where the primary name
     * might be in Kanji/Kana, but the search term is in Romaji.
     *
     * @param alias The artist alias to search for.
     * @return The current builder instance.
     */
    public MusicBrainzQueryBuilder withArtistAlias(String alias) {
        return appendCondition("alias", alias);
    }

    /**
     * Adds an advanced exact match condition using the OR operator for the artist.
     * It checks both the primary artist name and the aliases.
     *
     * @param nameOrAlias The artist name or alias.
     * @return The current builder instance.
     */
    public MusicBrainzQueryBuilder withArtistOrAlias(String nameOrAlias) {
        if (nameOrAlias == null || nameOrAlias.trim().isEmpty()) {
            return this;
        }
        String escapedValue = escapeLuceneCharacters(nameOrAlias);
        String condition = String.format("(artist:\"%s\" OR alias:\"%s\")", escapedValue, escapedValue);
        this.queryConditions.add(condition);
        return this;
    }

    /**
     * Builds the final Lucene query string.
     *
     * @return The formatted query string ready to be URL-encoded by the client.
     */
    public String build() {
        if (this.queryConditions.isEmpty()) {
            return "";
        }
        return String.join(" AND ", this.queryConditions);
    }

    /**
     * Appends a standard key-value condition to the query list.
     *
     * @param field The Lucene field name.
     * @param value The value to search for.
     * @return The current builder instance.
     */
    private MusicBrainzQueryBuilder appendCondition(String field, String value) {
        if (value == null || value.trim().isEmpty()) return this;

        String escapedValue = escapeLuceneCharacters(value);
        String condition = String.format("%s:\"%s\"", field, escapedValue);
        this.queryConditions.add(condition);

        return this;
    }

    /**
     * Escapes special characters used by Apache Lucene to prevent syntax errors.
     *
     * @param input The raw input string.
     * @return The escaped string.
     */
    private String escapeLuceneCharacters(String input) {
        return input.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
