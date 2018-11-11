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
import pl.kostrowski.lpmf.repository.PreProcesingCleanUpRepository;

import java.util.*;

@Service
public class BatchConvertLPMFFile {

    private final Logger LOG = LoggerFactory.getLogger(BatchConvertLPMFFile.class);

    private final PreProcesingCleanUpRepository preProcesingCleanUpRepository;
    private final JsoupHtmlDataParser jsoupFileParser;
    private final PersistUniqueBatch persistUniqueBatch;
    private final MovieConverter movieConverter;
    private final ArtistConverter artistConverter;
    private final SongTitleConverter songTitleConverter;
    private final NrAndDateOfListConverter nrAndDateOfListConverter;

    @Autowired
    public BatchConvertLPMFFile(PreProcesingCleanUpRepository preProcesingCleanUpRepository, JsoupHtmlDataParser jsoupFileParser, PersistUniqueBatch persistUniqueBatch, MovieConverter movieConverter, ArtistConverter artistConverter, SongTitleConverter songTitleConverter, NrAndDateOfListConverter nrAndDateOfListConverter) {
        this.preProcesingCleanUpRepository = preProcesingCleanUpRepository;
        this.jsoupFileParser = jsoupFileParser;
        this.persistUniqueBatch = persistUniqueBatch;
        this.movieConverter = movieConverter;
        this.artistConverter = artistConverter;
        this.songTitleConverter = songTitleConverter;
        this.nrAndDateOfListConverter = nrAndDateOfListConverter;
    }

    private void initMaps() {
        Map<String, String> movies = prepareMappings("movie");
        Map<String, String> artists = prepareMappings("artist");
        Map<String, String> songs = prepareMappings("song");

        movieConverter.setMoviesDictionary(movies);
        songTitleConverter.setSongDictionary(songs);
        artistConverter.setArtistDictionary(artists);

    }

    public void batchConvertAndPersist(Integer fromFile, Integer toFile) {

        initMaps();

        LOG.debug("Rozpoczęto parsowanie plików");
        List<List<SingleLpmfDto>> batchParsedFiles = jsoupFileParser.parseBatchFilesToObjects(fromFile, toFile);
        LOG.debug("Zakończono parsowanie plików");

        Set<ListInfo> listInfoS = new HashSet<>();
        Set<Movie> movieS = new HashSet<>();
        Set<Artist> artistS = new HashSet<>();
        Set<Song> songS = new HashSet<>();
        Set<LPMFPosition> lpmfPositionS = new HashSet<>();

        LOG.debug("Rozpoczęto parsowanie listInfo");
        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            ListInfo listInfo = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            listInfoS.add(listInfo);
        }
        Map<Integer, ListInfo> integerListInfoMap = persistUniqueBatch.persistListInfos(listInfoS);
        LOG.debug("Zakończono parsowanie listInfo");

        LOG.debug("Rozpoczęto parsowanie filmów");
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
        LOG.debug("Zakończono parsowanie filmów");

        LOG.debug("Rozpoczęto parsowanie artystów");
        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            listInfoTmp = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            ListInfo listInfo = integerListInfoMap.get(listInfoTmp.getNoOfList());

            for (SingleLpmfDto singleLpmfDto : batchParsedFile) {
                List<Artist> convert = artistConverter.convert(singleLpmfDto.getFullArtist(), listInfo);
                artistS.addAll(convert);
            }
        }
        Map<String, Artist> stringArtistMap = persistUniqueBatch.persistArtists(artistS);
        LOG.debug("Zakończono parsowanie artystów");

        LOG.debug("Rozpoczęto parsowanie utworów");
        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            listInfoTmp = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            ListInfo listInfo = integerListInfoMap.get(listInfoTmp.getNoOfList());

            for (SingleLpmfDto singleLpmfDto : batchParsedFile) {
                Movie convert = movieConverter.convert(singleLpmfDto.getFullMovieTitle(), listInfo);
                Movie movie = stringMovieMap.get(convert.toString());

                List<Artist> converted = artistConverter.convert(singleLpmfDto.getFullArtist(), listInfo);
                List<Artist> artistFromDb = new LinkedList<>();

                for (Artist artist : converted) {
                    artistFromDb.add(stringArtistMap.get(artist.toString()));
                }

                Song song = new Song();
                song = songTitleConverter.convert(singleLpmfDto.getFullSongTitle(), listInfo);
                song.setArtists(artistFromDb);
                song.setMovie(movie);
                song.setCoverLink(singleLpmfDto.getCoverLink());
                songS.add(song);
            }
        }
        Map<String, Song> stringSongMap = persistUniqueBatch.persistSongs(songS);
        LOG.debug("Zakończono parsowanie utworów");

        LOG.debug("Rozpoczęto parsowanie list");
        for (List<SingleLpmfDto> batchParsedFile : batchParsedFiles) {
            listInfoTmp = nrAndDateOfListConverter.convert(batchParsedFile.get(0).getNrAndDateOfList());
            ListInfo listInfo = integerListInfoMap.get(listInfoTmp.getNoOfList());

            for (SingleLpmfDto singleLpmfDto : batchParsedFile) {
                Movie convert = movieConverter.convert(singleLpmfDto.getFullMovieTitle(), listInfo);
                Movie movie = stringMovieMap.get(convert.toString());

                List<Artist> converted = artistConverter.convert(singleLpmfDto.getFullArtist(), listInfo);
                List<Artist> artistFromDb = new LinkedList<>();

                for (Artist artist : converted) {
                    artistFromDb.add(stringArtistMap.get(artist.toString()));
                }

                Song song = new Song();
                song = songTitleConverter.convert(singleLpmfDto.getFullSongTitle(), listInfo);
                song.setArtists(artistFromDb);
                song.setMovie(movie);

                Song songInlist = stringSongMap.get(song.toString());

                LPMFPosition lpmfPosition = new LPMFPosition();

                lpmfPosition.setListInfo(listInfo);
                lpmfPosition.setSong(song);
                lpmfPosition.setPos(singleLpmfDto.getPosition());
                lpmfPosition.setSong(songInlist);

                lpmfPositionS.add(lpmfPosition);
            }
            LOG.debug("Obrobiono listę " + listInfo.getNoOfList());

        }
        persistUniqueBatch.persistLPMFPosition(lpmfPositionS);
        LOG.debug("Zakończono parsowanie wszystkiego");
    }

    private Map<String, String> prepareMappings(String category) {

        Map<String, String> ret = new HashMap<>();
        List<PreProcesingCleanUpData> movie = preProcesingCleanUpRepository.findCleanUpDataByCategory(category);

        for (PreProcesingCleanUpData data : movie) {
            ret.put(data.getFrom(), data.getTo());
        }
        return ret;
    }

    public void markDuplicateSongs() {
        persistUniqueBatch.findDuplicatedSongs();
    }

}
