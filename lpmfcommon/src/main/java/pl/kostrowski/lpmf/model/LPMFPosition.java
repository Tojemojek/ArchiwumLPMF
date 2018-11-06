package pl.kostrowski.lpmf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "songs_in_list")
public class LPMFPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "pos")
    private Integer pos;

    @ManyToOne(cascade = CascadeType.MERGE)
    private ListInfo listInfo;

    @ManyToOne(cascade = CascadeType.MERGE)
    Song song;

}
