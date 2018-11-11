package pl.kostrowski.lpmf.dto.views;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "artists_medal_view")
public class MedalTableArtist {


    @Column(name = "artist_name")
    private String artistName;

    @Id
    @Column(name = "a_id")
    private Long id;

    private BigDecimal poz1;
    private BigDecimal poz2;
    private BigDecimal poz3;
    private BigDecimal poz4;
    private BigDecimal poz5;
    private BigDecimal poz6;
    private BigDecimal poz7;
    private BigDecimal poz8;
    private BigDecimal poz9;
    private BigDecimal poz10;
    private BigDecimal poz11;
    private BigDecimal poz12;
    private BigDecimal poz13;
    private BigDecimal poz14;
    private BigDecimal poz15;
    private BigDecimal poz16;
    private BigDecimal poz17;
    private BigDecimal poz18;
    private BigDecimal poz19;
    private BigDecimal poz20;

    @Transient
    private Integer totalInList;

    @Transient
    private Map<Integer, Integer> medals = new HashMap<>();

    public void prepare(){
        medals.put(1,poz1.intValue());
        medals.put(2,poz2.intValue());
        medals.put(3,poz3.intValue());
        medals.put(4,poz4.intValue());
        medals.put(5,poz5.intValue());
        medals.put(6,poz6.intValue());
        medals.put(7,poz7.intValue());
        medals.put(8,poz8.intValue());
        medals.put(9,poz9.intValue());
        medals.put(10,poz10.intValue());
        medals.put(11,poz11.intValue());
        medals.put(12,poz12.intValue());
        medals.put(13,poz13.intValue());
        medals.put(14,poz14.intValue());
        medals.put(15,poz15.intValue());
        medals.put(16,poz16.intValue());
        medals.put(17,poz17.intValue());
        medals.put(18,poz18.intValue());
        medals.put(19,poz19.intValue());
        medals.put(20,poz20.intValue());

        totalInList = 0;

        for (int i = 1; i <= 20; i++) {
            totalInList += medals.get(i);
        }
    }
}
