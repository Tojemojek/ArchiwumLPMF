package pl.kostrowski.lpmf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "coverLink")
    private String coverLink;

    @Column(name = "title")
    private String title;

    @Column(name ="has_duplicates")
    private Boolean hasDuplicates;

    @ManyToOne(cascade = CascadeType.MERGE)
    private ListInfo firstTimeInList;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Movie movie;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "song_has_artist",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<Artist> artists;

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", movie=" + movie +
                ", artists=" + artists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
                Objects.equals(movie.getTitle(), song.movie.getTitle()) &&
                areArtistsSame(artists,song.artists);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Artist artist : artists) {
            hash += Objects.hash(artist);
        }
        return Objects.hash(title, movie, hash);
    }

    private boolean areArtistsSame(List<Artist> artists, List<Artist> songArtists){

        if (artists == null && songArtists == null){
            return true;
        }

        if (artists != null && songArtists == null || artists == null && songArtists != null){
            return false;
        }

        if (artists.size() != songArtists.size()){
            return false;
        }

        List<String> strings = new LinkedList<>();

        for (Artist artist : artists) {
            strings.add(artist.toString());
        }

        for (Artist songArtist : songArtists) {
            if (!strings.contains(songArtist.toString())){
                return false;
            }
        }
        return true;
    }

}