package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.converters.ArtistConverter;
import pl.kostrowski.lpmf.converters.MovieConverter;
import pl.kostrowski.lpmf.converters.NrAndDateOfListConverter;
import pl.kostrowski.lpmf.converters.SongTitleConverter;
import pl.kostrowski.lpmf.dto.SingleLpmfDto;
import pl.kostrowski.lpmf.jsoup.JsoupHtmlDataParser;
import pl.kostrowski.lpmf.model.*;
import pl.kostrowski.lpmf.repository.CleanUpRepository;

import java.util.*;

@Service
public class ConvertSingleLPMFFile {

    private final Logger LOG = LoggerFactory.getLogger(ConvertSingleLPMFFile.class);

    JsoupHtmlDataParser jsoupFileParser;
    PersistUnique persistUnique;
    MovieConverter movieConverter;
    ArtistConverter artistConverter;
    SongTitleConverter songTitleConverter;
    NrAndDateOfListConverter nrAndDateOfListConverter;

    @Autowired
    public ConvertSingleLPMFFile(JsoupHtmlDataParser jsoupFileParser, PersistUnique persistUnique, MovieConverter movieConverter, ArtistConverter artistConverter, SongTitleConverter songTitleConverter, NrAndDateOfListConverter nrAndDateOfListConverter) {
        this.jsoupFileParser = jsoupFileParser;
        this.persistUnique = persistUnique;
        this.movieConverter = movieConverter;
        this.artistConverter = artistConverter;
        this.songTitleConverter = songTitleConverter;
        this.nrAndDateOfListConverter = nrAndDateOfListConverter;
    }

    public void convertAndPersistSingleLPMFFile(Integer noOfProcessedFile) {

        List<SingleLpmfDto> parsedFile = new LinkedList<>();
        parsedFile = jsoupFileParser.parseSingleFileToObjects(noOfProcessedFile);

        ListInfo listInfo = nrAndDateOfListConverter.convert(parsedFile.get(0).getNrAndDateOfList());
        listInfo = persistUnique.persistListInfo(listInfo);

        for (SingleLpmfDto singleEntryInListDto : parsedFile) {

            Movie movie = new Movie();
            List<Artist> artists = new LinkedList<>();
            Song song = new Song();
            LPMFPosition lpmfPosition = new LPMFPosition();

            movie = movieConverter.convert(singleEntryInListDto.getFullMovieTitle(), listInfo);
            movie = persistUnique.persistMovie(movie);

            artists = artistConverter.convert(singleEntryInListDto.getFullArtist(), listInfo);
            artists = persistUnique.persistArtists(artists);

            song = songTitleConverter.convert(singleEntryInListDto.getFullSongTitle(), listInfo);
            song.setArtists(artists);
            song.setMovie(movie);
            song.setCoverLink(singleEntryInListDto.getCoverLink());

            song = persistUnique.persistSong(song);

            lpmfPosition.setListInfo(listInfo);
            lpmfPosition.setSong(song);
            lpmfPosition.setPos(singleEntryInListDto.getPosition());
            persistUnique.persistLPMFPosition(lpmfPosition);

        }
    }
    public void markDuplicateSongs() {
        persistUnique.findDuplicatedSongs();
    }
}
