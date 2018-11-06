package pl.kostrowski.lpmf.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "list_info")
public class ListInfo{

    @Id
    @Column(name = "no_Of_List")
    private Integer noOfList;

    @Column(name = "date_of_list")
    private LocalDate dateOfList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListInfo listInfo = (ListInfo) o;
        return Objects.equals(noOfList, listInfo.noOfList) &&
                Objects.equals(dateOfList, listInfo.dateOfList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOfList, dateOfList);
    }
}
