package pl.kostrowski.lpmf.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "raw_lpmf_data")
public class RawLpmfData {

    @Id
    @Column(name = "id")
    Integer id;

    @Lob
    @Column(name = "rawPage")
    String rawPage;

}
