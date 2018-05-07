package pl.kostrowski.lpmf.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.converters.ArtistConverter;
import pl.kostrowski.lpmf.converters.FilmTitleConverter;
import pl.kostrowski.lpmf.converters.SongTitleConverter;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;

@Service
public class MakeUnique {

    private final Logger LOG = LoggerFactory.getLogger(MakeUnique.class);

    @Autowired
    JsoupFileParser jsoupFileParser;

    @Autowired
    FilmTitleConverter filmTitleConverter;

    @Autowired
    ArtistConverter artistConverter;

    @Autowired
    SongTitleConverter songTitleConverter;

    @Autowired
    PersistUnique persistUnique;

    public void persistUniqueMovies(int maxList) {

        List<SingleEntryInListDto> singleEntryInListDtos = new LinkedList<>();
        List<Movie> movies = new LinkedList<>();
        int newMoviesBefore = 0;
        int newMoviesAfter = 0;
        int deltaMovies = 0;

        for (int i = 0; i <= maxList; i++) {
            singleEntryInListDtos = jsoupFileParser.makeDOMfor(i);
            movies = filmTitleConverter.convert(singleEntryInListDtos);

            newMoviesBefore = PersistUnique.getNewMoviesCount();

            for (Movie movie : movies) {
                persistUnique.persistMovie(movie);
            }
            newMoviesAfter = PersistUnique.getNewMoviesCount();
            deltaMovies = newMoviesAfter - newMoviesBefore;

            LOG.info("W pliku nr: " + i + " nowych filmów jest: " + deltaMovies);

        }
    }

    public void persistUniqueArtists(int maxList) {

        List<SingleEntryInListDto> singleEntryInListDtos = new LinkedList<>();
        List<Artist> artists = new LinkedList<>();
        int newArtistsBefore = 0;
        int newArtistsAfter = 0;
        int deltaArtists = 0;

        for (int i = 0; i <= maxList; i++) {
            singleEntryInListDtos = jsoupFileParser.makeDOMfor(i);
            artists = artistConverter.convert(singleEntryInListDtos);

            newArtistsBefore = PersistUnique.getNewArtistCount();

            for (Artist artist : artists) {
                persistUnique.persistArtist(artist);
            }

            newArtistsAfter = PersistUnique.getNewArtistCount();

            deltaArtists = newArtistsAfter - newArtistsBefore;

            LOG.info("W pliku nr: " + i + " nowych artystów jest: " + deltaArtists);

        }

    }

    public void persistUniqueSongs(int maxList) {

        List<SingleEntryInListDto> singleEntryInListDtos = new LinkedList<>();
        List<Song> songs = new LinkedList<>();
        int newSongsBefore = 0;
        int newSongsAfter = 0;
        int deltaSongs = 0;

        for (int i = 0; i <= maxList; i++) {
            singleEntryInListDtos = jsoupFileParser.makeDOMfor(i);
            songs = songTitleConverter.convert(singleEntryInListDtos);

            newSongsBefore = PersistUnique.getNewSongCount();

            for (Song song : songs) {
                persistUnique.persistSong(song);
            }

            newSongsAfter = PersistUnique.getNewSongCount();

            deltaSongs = newSongsAfter - newSongsBefore;

            LOG.info("W pliku nr: " + i + " nowych piosenek jest: " + deltaSongs);
        }
    }
}
