package pl.kostrowski.lpmf.dto;

import pl.kostrowski.lpmf.model.LPMFPosition;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class LPMFPositionWrapperDto {

    private List<LPMFPosition> lpmfPositionList = new LinkedList<>();
    private LocalDate dateOfList;
    private Integer noOfList;

    public LPMFPositionWrapperDto() {
    }

    public LPMFPositionWrapperDto(List<LPMFPosition> lpmfPositionList, LocalDate dateOfList, Integer noOfList) {
        this.lpmfPositionList = lpmfPositionList;
        this.dateOfList = dateOfList;
        this.noOfList = noOfList;
    }


    public List<LPMFPosition> getLpmfPositionList() {
        return lpmfPositionList;
    }

    public void setLpmfPositionList(List<LPMFPosition> lpmfPositionList) {
        this.lpmfPositionList = lpmfPositionList;
    }

    public LocalDate getDateOfList() {
        return dateOfList;
    }

    public void setDateOfList(LocalDate dateOfList) {
        this.dateOfList = dateOfList;
    }

    public Integer getNoOfList() {
        return noOfList;
    }

    public void setNoOfList(Integer noOfList) {
        this.noOfList = noOfList;
    }
}
