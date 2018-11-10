package pl.kostrowski.lpmf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LPMFPosition that = (LPMFPosition) o;
        return Objects.equals(pos, that.pos) &&
                Objects.equals(listInfo.getNoOfList(), that.listInfo.getNoOfList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, listInfo);
    }
}
