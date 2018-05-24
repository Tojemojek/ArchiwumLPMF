package pl.kostrowski.lpmf.dto;

import java.util.HashMap;
import java.util.Map;

public class MedalTableArtist {

    private String artistName;
    private Integer totalInList;
    private Map<Integer, Integer> medals = new HashMap<>();

    public MedalTableArtist() {
    }

    public MedalTableArtist(String artistName, Map<Integer, Integer> medals, Integer totalInList) {
        this.artistName = artistName;
        this.medals = medals;
        this.totalInList = totalInList;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Map<Integer, Integer> getMedals() {
        return medals;
    }

    public void setMedals(Map<Integer, Integer> medals) {
        this.medals = medals;
    }

    public Integer getTotalInList() {
        return totalInList;
    }

    public void setTotalInList(Integer totalInList) {
        this.totalInList = totalInList;
    }
}
