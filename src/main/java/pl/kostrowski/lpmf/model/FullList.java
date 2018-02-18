package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fullLists")
public class FullList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "noOfList")
    private Integer noOfList;

    @Column(name = "date")
    private String date;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "full_list_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_in_list_id")}
    )
    List<SongInList> songs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNoOfList() {
        return noOfList;
    }

    public void setNoOfList(Integer noOfList) {
        this.noOfList = noOfList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SongInList> getSongs() {
        return songs;
    }

    public void setSongs(List<SongInList> songs) {
        this.songs = songs;
    }
}
