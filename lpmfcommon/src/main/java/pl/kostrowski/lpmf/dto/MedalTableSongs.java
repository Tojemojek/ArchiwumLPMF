package pl.kostrowski.lpmf.dto;

import java.util.HashMap;
import java.util.Map;

public class MedalTableSongs {

    private String songTitle;
    private String movieTitle;
    private Integer totalInList;
    private Map<Integer, Integer> medals = new HashMap<>();

    public MedalTableSongs() {
    }

    public MedalTableSongs(String songTitle, String movieTitle, Integer totalInList, Map<Integer, Integer> medals) {
        this.songTitle = songTitle;
        this.movieTitle = movieTitle;
        this.totalInList = totalInList;
        this.medals = medals;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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
