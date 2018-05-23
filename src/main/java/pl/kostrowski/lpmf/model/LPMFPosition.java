package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;
import pl.kostrowski.lpmf.dto.MedalTableSongs;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Entity
@Table(name = "songs_in_list")
@SqlResultSetMapping(
        name= "medalTableMapping",
        classes = @ConstructorResult(
        targetClass = MedalTableSongs.class,
                columns = {
                                @ColumnResult(name="poz1",type = Integer.class),
                                @ColumnResult(name="poz2",type = Integer.class),
                                @ColumnResult(name="poz3",type = Integer.class),
                                @ColumnResult(name="poz4",type = Integer.class),
                                @ColumnResult(name="poz5",type = Integer.class),
                                @ColumnResult(name="poz6",type = Integer.class),
                                @ColumnResult(name="poz7",type = Integer.class),
                                @ColumnResult(name="poz8",type = Integer.class),
                                @ColumnResult(name="poz9",type = Integer.class),
                                @ColumnResult(name="poz10",type = Integer.class),
                                @ColumnResult(name="poz11",type = Integer.class),
                                @ColumnResult(name="poz12",type = Integer.class),
                                @ColumnResult(name="poz13",type = Integer.class),
                                @ColumnResult(name="poz14",type = Integer.class),
                                @ColumnResult(name="poz15",type = Integer.class),
                                @ColumnResult(name="poz16",type = Integer.class),
                                @ColumnResult(name="poz17",type = Integer.class),
                                @ColumnResult(name="poz18",type = Integer.class),
                                @ColumnResult(name="poz19",type = Integer.class),
                                @ColumnResult(name="poz20",type = Integer.class),
                                @ColumnResult(name="totalInList",type = Integer.class),
                                @ColumnResult(name="songTitle",type = String.class),
                                @ColumnResult(name="movieTitle",type = String.class)

                        }
                )
        )
public class LPMFPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "pos")
    private Integer pos;

    @Column(name = "no_Of_List")
    private Integer noOfList;

    @Column(name = "date_of_list")
    private LocalDate dateOfList;

    @ManyToOne(cascade = CascadeType.MERGE)
    Song song;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Integer getNoOfList() {
        return noOfList;
    }

    public void setNoOfList(Integer noOfList) {
        this.noOfList = noOfList;
    }

    public LocalDate getDateOfList() {
        return dateOfList;
    }

    public void setDateOfList(LocalDate dateOfList) {
        this.dateOfList = dateOfList;
    }
}
