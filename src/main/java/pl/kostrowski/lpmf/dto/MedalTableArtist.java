package pl.kostrowski.lpmf.dto;

import java.util.HashMap;
import java.util.Map;

public class MedalTableArtist {

    private String artistName;
    private Long artistId;
    private Integer totalInList;
    private Map<Integer, Integer> medals = new HashMap<>();

    public MedalTableArtist() {
    }

    public MedalTableArtist(String artistName, Long artistId, Integer totalInList, Map<Integer, Integer> medals) {
        this.artistName = artistName;
        this.artistId = artistId;
        this.totalInList = totalInList;
        this.medals = medals;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Integer getTotalInList() {
        return totalInList;
    }

    public void setTotalInList(Integer totalInList) {
        this.totalInList = totalInList;
    }

    public Map<Integer, Integer> getMedals() {
        return medals;
    }

    public void setMedals(Map<Integer, Integer> medals) {
        this.medals = medals;
    }
}
