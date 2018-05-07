package pl.kostrowski.lpmf.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.model.dictionaries.AllDictionaries;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

@Service
public class PersistUnique {

    private final Logger LOG = LoggerFactory.getLogger(PersistUnique.class);
    private static int newMoviesCount = 0;
    private static int newArtistCount = 0;
    private static int newSongCount = 0;

    @Autowired
    MovieRepository mr;

    @Autowired
    ArtistRepository ar;

    @Autowired
    SongRepository sr;

    public Movie persistMovie(Movie movie) {

        Movie movieFromDB = null;

        try {
            movieFromDB = mr.findMovieByTitle(movie.getTitle());
        } catch (Exception e) {

        }
        if (movieFromDB != null) {
            movie = movieFromDB;
        } else {
            mr.save(movie);
            LOG.debug(movie + "dodano do bazy danych");
            newMoviesCount++;
        }

        return movie;
    }

    public Artist persistArtist(Artist artist) {

        Artist artistFromDB = null;

        try {
            artistFromDB = ar.findArtistByFullName(artist.getFullName());
        } catch (Exception e) {

        }
        if (artistFromDB != null) {
            artist = artistFromDB;
        } else {
            ar.save(artist);
            LOG.debug(artist + "dodano do bazy danych");
            newArtistCount++;
        }

        return artist;
    }

    public Song persistSong(Song song) {

        Song songFromDb = null;

        try {
            songFromDb = sr.findSongByTitleAndMovie(song.getTitle(), song.getMovie());
        } catch (Exception e) {

        }
        if (songFromDb != null) {
            song = songFromDb;
        } else {
            sr.save(song);
            LOG.debug(song + "dodano do bazy danych");
            newSongCount++;
        }

        return song;
    }




    public static int getNewMoviesCount() {
        return newMoviesCount;
    }
    public static int getNewArtistCount() {
        return newArtistCount;
    }
    public static int getNewSongCount() {
        return newSongCount;
    }


}
