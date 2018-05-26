package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.MedalTableArtist;
import pl.kostrowski.lpmf.dto.MedalTableMovie;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MedalTablesRepository;
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
    MedalTablesRepository medalTablesRepository;


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

    public List<MedalTableSongs> medalTableSongs() {
        List<MedalTableSongs> medalTableDataFromDb = medalTablesRepository.createMedalTableSongsFromDb();
        return medalTableDataFromDb;
    }

    public List<MedalTableArtist> medalTableArtists() {
        List<MedalTableArtist> medalTableDataFromDb = medalTablesRepository.createMedalTableArtistFromDb();
        return medalTableDataFromDb;
    }

    public List<MedalTableMovie> medalTableMovies() {
        List<MedalTableMovie> medalTableDataFromDb = medalTablesRepository.createMedalTableMoviesFromDb();
        return medalTableDataFromDb;
    }

}
