package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MedalTableSongsRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.List;

@Service
public class DisplayService {

    @Autowired
    SongRepository songRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MedalTableSongsRepository medalTableSongsRepository;


    public List<Song> showAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs;
    }

    public List<Movie> showAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public List<Artist> showAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists;
    }

    public List<MedalTableSongs> medalTable() {
        List<MedalTableSongs> medalTableDataFromDb = medalTableSongsRepository.createMedalTableDataFromDb();
        return medalTableDataFromDb;
    }
}
