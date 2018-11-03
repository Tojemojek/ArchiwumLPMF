package pl.kostrowski.lpmf.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "songs_in_list")
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
