package pl.kostrowski.lpmf.dto;

import java.util.HashMap;
import java.util.Map;

public class MedalTableMovie {

    private String movieTitle;
    private Integer totalInList;
    private Map<Integer, Integer> medals = new HashMap<>();

    public MedalTableMovie() {
    }

    public MedalTableMovie(String movieTitle, Integer totalInList, Map<Integer, Integer> medals) {
        this.movieTitle = movieTitle;
        this.totalInList = totalInList;
        this.medals = medals;
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
