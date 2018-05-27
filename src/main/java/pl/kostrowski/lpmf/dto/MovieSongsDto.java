package pl.kostrowski.lpmf.dto;

import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;

import java.util.List;

public class MovieSongsDto {

    private Movie movie;
    private List<Song> songList;

    public MovieSongsDto() {
    }

    public MovieSongsDto(Movie movie, List<Song> songList) {
        this.movie = movie;
        this.songList = songList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
