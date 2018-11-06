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
public class BatchConvertLPMFFile {

    private final Logger LOG = LoggerFactory.getLogger(BatchConvertLPMFFile.class);

    CleanUpRepository cleanUpRepository;
    JsoupHtmlDataParser jsoupFileParser;
    PersistUniqueBatch persistUniqueBatch;
    MovieConverter movieConverter;
    ArtistConverter artistConverter;
    SongTitleConverter songTitleConverter;
    NrAndDateOfListConverter nrAndDateOfListConverter;

    @Autowired
    public BatchConvertLPMFFile(CleanUpRepository cleanUpRepository, JsoupHtmlDataParser jsoupFileParser, PersistUniqueBatch persistUniqueBatch, MovieConverter movieConverter, ArtistConverter artistConverter, SongTitleConverter songTitleConverter, NrAndDateOfListConverter nrAndDateOfListConverter) {
        this.cleanUpRepository = cleanUpRepository;
        this.jsoupFileParser = jsoupFileParser;
        this.persistUniqueBatch = persistUniqueBatch;
        this.movieConverter = movieConverter;
        this.artistConverter = artistConverter;
        this.songTitleConverter = songTitleConverter;
        this.nrAndDateOfListConverter = nrAndDateOfListConverter;
    }

    public void batchConvertAndPersist(Integer fromFile, Integer toFile) {

        Map<String, String> movies = prepareMappings("movie");
        Map<String, String> artist = prepareMappings("artist");
        Map<String, String> songs = prepareMappings("song");

        List<List<SingleLpmfDto>> batchParsedFiles = jsoupFileParser.parseBatchFilesToObjects(fromFile, toFile);

        Set<ListInfo> listInfoS = new HashSet<>();
        Set<Movie> movieS = new HashSet<>();
        Set<Artist> artistS = new HashSet<>();

        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            ListInfo listInfo = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            listInfoS.add(listInfo);
        }
        Map<Integer, ListInfo> integerListInfoMap = persistUniqueBatch.persistListInfos(listInfoS);

        ListInfo listInfoTmp;
        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            listInfoTmp = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            ListInfo listInfo = integerListInfoMap.get(listInfoTmp.getNoOfList());

            for (SingleLpmfDto singleLpmfDto : batchParsedFile) {
                Movie convert = movieConverter.convert(singleLpmfDto.getFullMovieTitle(), listInfo);
                movieS.add(convert);
            }
        }
        Map<String, Movie> stringMovieMap = persistUniqueBatch.persistMovies(movieS);

        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            listInfoTmp = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            ListInfo listInfo = integerListInfoMap.get(listInfoTmp.getNoOfList());

            for (SingleLpmfDto singleLpmfDto : batchParsedFile) {
                List<Artist> convert = artistConverter.convert(singleLpmfDto.getFullArtist(), listInfo);
                artistS.addAll(convert);
            }
        }
        Map<String, Artist> stringArtistMap = persistUniqueBatch.persistArtists(artistS);


        //todo dokończyć

    }

    private Map<String, String> prepareMappings(String category) {

        Map<String, String> ret = new HashMap<>();
        List<CleanUpData> movie = cleanUpRepository.findCleanUpDataByCategory(category);

        for (CleanUpData data : movie) {
            ret.put(data.getFrom(), data.getTo());
        }
        return ret;
    }
}
