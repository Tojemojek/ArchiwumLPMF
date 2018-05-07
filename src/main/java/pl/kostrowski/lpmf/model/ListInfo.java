package pl.kostrowski.lpmf.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "list_info")
public class ListInfo{

    @Id
    @Column(name = "noOfList")
    private Integer noOfList;

    @Column(name = "date")
    private LocalDate date;

    public ListInfo() {
    }

    public Integer getNoOfList() {
        return noOfList;
    }

    public void setNoOfList(Integer noOfList) {
        this.noOfList = noOfList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListInfo listInfo = (ListInfo) o;
        return Objects.equals(noOfList, listInfo.noOfList) &&
                Objects.equals(date, listInfo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOfList, date);
    }
}
