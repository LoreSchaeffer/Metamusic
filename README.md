# Metamusic API

A robust, enterprise-grade Java 25 client library for aggregating music metadata from multiple external providers, including MusicBrainz and Apple iTunes.

## Features

* **Modern Java:** Built for Java 25, leveraging modern language features like `record` classes and the native `HttpClient`.
* **Virtual Threads Ready:** Fully compatible with Java's structured concurrency and virtual threads for high-performance, parallel API fetching.
* **Resilient:** Includes custom domain exceptions and strict timeout handling to prevent cascading failures in your backend.

## Installation

This library is distributed via [JitPack](https://jitpack.io). You can easily include it in your Maven or Gradle project.

### Maven

**Step 1:** Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>[https://jitpack.io](https://jitpack.io)</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.LoreSchaeffer</groupId>
        <artifactId>Metamusic</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```

### Quick Start

The library provides individual clients for different providers, all implementing the MusicProvider interface for easy polymorphism in frameworks like Spring Boot.

#### Using the iTunes Client
The iTunes client is extremely fast and provides immediate access to preview URLs and artwork.

```Java
import it.lycoris.metamusic.itunes.ITunesClient;
import it.lycoris.metamusic.itunes.dto.ITunesSearchResponse;

public class ExampleUsage {
    public static void main(String[] args) {
        // Initialize the client with a descriptive User-Agent
        ITunesClient iTunesClient = new ITunesClient("MyAwesomeApp/1.0 ( contact@example.com )");

        // Search for tracks (returns ITunesSearchResponse)
        ITunesSearchResponse response = iTunesClient.search("Queen Bohemian Rhapsody");

        System.out.println("Total results: " + response.resultCount());

        // Assuming ITunesSearchResponse contains a list of results
        response.results().forEach(track -> {
            System.out.println("Title: " + track.trackName());
            System.out.println("Artist: " + track.artistName());
        });
    }
}
```

#### Using the MusicBrainz Client
MusicBrainz provides highly detailed metadata. Note that MusicBrainz requires a strict User-Agent and enforces rate limiting (1 request per second per IP).

```Java
import it.lycoris.metamusic.musicbrainz.MusicBrainzClient;
import it.lycoris.metamusic.musicbrainz.dto.MusicBrainzSearchResponse;
import it.lycoris.metamusic.musicbrainz.dto.CoverArtResponse;
import java.util.Optional;

public class ExampleUsage {
    public static void main(String[] args) {
        // Initialize the client
        MusicBrainzClient musicBrainzClient = new MusicBrainzClient("MyAwesomeApp/1.0 ( contact@example.com )");

        // Search for tracks with custom limit and offset
        MusicBrainzSearchResponse response = musicBrainzClient.search("Daft Punk");

        System.out.println("Total matches in database: " + response.count());

        response.results().forEach(result -> {
            System.out.println("Title: " + result.title());
            System.out.println("MBID: " + result.id());

            // Example of fetching cover art for the first release of the result
            if (result.releases() != null && !result.releases().isEmpty()) {
                String releaseId = result.releases().getFirst().id();
                Optional<CoverArtResponse> coverArt = musicBrainzClient.getCoverArt(releaseId);

                coverArt.ifPresent(art ->
                        System.out.println("Cover Art Found! URL: " + art.images().getFirst().image())
                );
            }
        });
    }
}
```

### Error Handling
All network operations and API errors are wrapped in a robust exception hierarchy extending MusicProviderException, ensuring your application can gracefully handle HTTP 4xx, 5xx, or timeouts.

### License
This project is licensed under the [MIT](https://github.com/LoreSchaeffer/Metamusic/blob/main/LICENSE) License.