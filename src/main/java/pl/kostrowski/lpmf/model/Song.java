package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    private List<Artist> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Artist> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Artist> authors) {
        this.authors = authors;
    }

    public ListInfo getFirstTimeInList() {
        return firstTimeInList;
    }

    public void setFirstTimeInList(ListInfo firstTimeInList) {
        this.firstTimeInList = firstTimeInList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
                Objects.equals(movie, song.movie) &&
                Objects.equals(authors, song.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, movie, authors);
    }
}
