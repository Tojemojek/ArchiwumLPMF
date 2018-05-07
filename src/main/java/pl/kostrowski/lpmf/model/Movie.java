package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "first_time_in_list")
    private String firstTimeInList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstTimeInList() {
        return firstTimeInList;
    }

    public void setFirstTimeInList(String firstTimeInList) {
        this.firstTimeInList = firstTimeInList;
    }
}
