package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.converters.ArtistConverter;
import pl.kostrowski.lpmf.converters.MovieConverter;
import pl.kostrowski.lpmf.converters.NrAndDateOfListConverter;
import pl.kostrowski.lpmf.converters.SongTitleConverter;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConvertSingleLPMFFile {

    private final Logger LOG = LoggerFactory.getLogger(ConvertSingleLPMFFile.class);

    @Autowired
    JsoupFileParser jsoupFileParser;

    @Autowired
    PersistUnique persistUnique;

    @Autowired
    MovieConverter movieConverter;

    @Autowired
    ArtistConverter artistConverter;

    @Autowired
    SongTitleConverter songTitleConverter;

    @Autowired
    NrAndDateOfListConverter nrAndDateOfListConverter;


    public void convertAndPersistSingleLPMFFile(Integer noOfProcessedFile) {

        List<SingleEntryInListDto> parsedFile = new LinkedList<>();
        parsedFile = jsoupFileParser.makeDOMfor(noOfProcessedFile);

        ListInfo listInfo = nrAndDateOfListConverter.convert(parsedFile.get(0).getNrAndDateOfList());
        listInfo = persistUnique.persistListInfo(listInfo);

        for (SingleEntryInListDto singleEntryInListDto : parsedFile) {

            Movie movie = new Movie();
            List<Artist> artists = new LinkedList<>();
            Song song = new Song();

            movie = movieConverter.convert(singleEntryInListDto.getFullMovieTitle(), listInfo);
            movie = persistUnique.persistMovie(movie);

            artists = artistConverter.convert(singleEntryInListDto.getFullArtist(), listInfo);
            artists = persistUnique.persistArtists(artists);

            song = songTitleConverter.convert(singleEntryInListDto.getFullSongTitle(), listInfo);
            song.setAuthors(artists);
            song.setMovie(movie);

            song = persistUnique.persistSong(song);
            song.setCoverLink(singleEntryInListDto.getCoverLink());
        }
    }
}