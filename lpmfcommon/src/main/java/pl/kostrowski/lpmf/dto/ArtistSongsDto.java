package pl.kostrowski.lpmf.dto;

import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Song;

import java.util.List;

public class ArtistSongsDto {

    private Artist artist;
    private List<Song> songList;

    public ArtistSongsDto() {
    }

    public ArtistSongsDto(Artist artist, List<Song> songList) {
        this.artist = artist;
        this.songList = songList;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
