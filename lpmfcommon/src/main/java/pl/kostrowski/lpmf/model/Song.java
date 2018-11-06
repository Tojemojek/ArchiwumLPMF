package pl.kostrowski.lpmf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

}
