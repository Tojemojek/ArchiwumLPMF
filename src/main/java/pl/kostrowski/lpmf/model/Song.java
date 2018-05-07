package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "coverLink")
    private String coverLink;

    @Column(name = "title")
    private String title;

    @Column(name = "first_time_in_list")
    private String firstTimeInList;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Movie movie;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "song_has_artist",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<Artist> authors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFirstTimeInList() {
        return firstTimeInList;
    }

    public void setFirstTimeInList(String firstTimeInList) {
        this.firstTimeInList = firstTimeInList;
    }

}
