package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.ArtistSongsDto;
import pl.kostrowski.lpmf.dto.LPMFPositionWrapperDto;
import pl.kostrowski.lpmf.dto.MovieSongsDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class DisplayService {

    private final LPMFPositionRepository lpmfPositionRepository;

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    private final MovieRepository movieRepository;

    @Autowired
    public DisplayService(LPMFPositionRepository lpmfPositionRepository, SongRepository songRepository, ArtistRepository artistRepository, MovieRepository movieRepository) {
        this.lpmfPositionRepository = lpmfPositionRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.movieRepository = movieRepository;
    }

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

    public List<LPMFPosition> findByNoOfList(Integer listNo) {
        List<LPMFPosition> byNoOfListOOrderByPos = lpmfPositionRepository.findByNoOfListOrderByPos(listNo);
        return byNoOfListOOrderByPos;
    }

    public List<LPMFPosition> customFindBySongId(Long songId) {
        List<LPMFPosition> byNoOfListOOrderByPos = lpmfPositionRepository.customFindBySongId(songId);

        if (byNoOfListOOrderByPos.get(0).getSong().getHasDuplicates() != null && byNoOfListOOrderByPos.get(0).getSong().getHasDuplicates()) {
            byNoOfListOOrderByPos = lpmfPositionRepository.
                    customFindBySongTitleAndMovieTitle(
                            byNoOfListOOrderByPos.get(0).getSong().getTitle(),
                            byNoOfListOOrderByPos.get(0).getSong().getMovie().getTitle());
        }

        return byNoOfListOOrderByPos;
    }

    public List<LPMFPositionWrapperDto> showAllLists() {
        List<LPMFPosition> all = lpmfPositionRepository.findAll();
        List<LPMFPositionWrapperDto> allDto = new LinkedList<>();
        LPMFPositionWrapperDto tmpWrapper;

        int counter = 0;
        List<LPMFPosition> tmp = new LinkedList<>();
        for (LPMFPosition lpmfPosition : all) {
            counter++;
            tmp.add(lpmfPosition);
            if (counter % 20 == 0) {
                tmpWrapper = new LPMFPositionWrapperDto();
                tmpWrapper.setNoOfList(lpmfPosition.getListInfo().getNoOfList());
                tmpWrapper.setDateOfList(lpmfPosition.getListInfo().getDateOfList());
                tmpWrapper.getLpmfPositionList().addAll(tmp);
                allDto.add(tmpWrapper);
                tmp.clear();
            }
        }

        return allDto;
    }

    public ArtistSongsDto customFindArtistById(Long artistId) {

        Artist artist = artistRepository.findById(artistId).get();
        List<Song> songsByArtist = songRepository.customFindByArtistId(artistId);

        ArtistSongsDto artistSongsDto = new ArtistSongsDto(artist, songsByArtist);

        return artistSongsDto;

    }

    public MovieSongsDto customFindMovieById(Long movieId) {

        Movie movie = movieRepository.findById(movieId).get();
        List<Song> songsByMovie = songRepository.customFindByMovieId(movieId);

        MovieSongsDto movieSongsDto = new MovieSongsDto(movie, songsByMovie);

        return movieSongsDto;

    }
}
