package pl.kostrowski.lpmf.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "list_info")
public class ListInfo{

    @Id
    @Column(name = "no_Of_List")
    private Integer noOfList;

    @Column(name = "date_of_list")
    private LocalDate dateOfList;

    public ListInfo() {
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
