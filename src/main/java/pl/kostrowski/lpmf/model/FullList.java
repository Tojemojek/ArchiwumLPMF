package pl.kostrowski.lpmf.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fullLists")
public class FullList {

    @Id
    @Column(name = "noOfList")
    private Integer noOfList;

    @Column(name = "date")
    private String date;

}
