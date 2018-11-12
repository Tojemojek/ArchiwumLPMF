package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.ArtistSongsDto;
import pl.kostrowski.lpmf.dto.MovieSongsDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.List;

@Service
public class DisplayAllService {

    private final LPMFPositionRepository lpmfPositionRepository;

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    private final MovieRepository movieRepository;

    @Autowired
    public DisplayAllService(LPMFPositionRepository lpmfPositionRepository, SongRepository songRepository, ArtistRepository artistRepository, MovieRepository movieRepository) {
        this.lpmfPositionRepository = lpmfPositionRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.movieRepository = movieRepository;
    }

    public List<LPMFPosition> findByNoOfList(Integer listNo) {
        return lpmfPositionRepository.findByNoOfListOrderByPos(listNo);
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

    public ArtistSongsDto customFindArtistById(Long artistId) {

        Artist artist = artistRepository.findById(artistId).get();
        List<Song> songsByArtist = songRepository.customFindByArtistId(artistId);

        return new ArtistSongsDto(artist, songsByArtist);

    }

    public MovieSongsDto customFindMovieById(Long movieId) {

        Movie movie = movieRepository.findById(movieId).get();
        List<Song> songsByMovie = songRepository.customFindByMovieId(movieId);

        return new MovieSongsDto(movie, songsByMovie);

    }

    public Page<Song> pagableShowAllSongs(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Sort sort1 = new Sort(Sort.Direction.ASC, "title");
        Sort sort2 = new Sort(Sort.Direction.ASC, "movie.title");
        Sort sort3 = new Sort(Sort.Direction.ASC, "firstTimeInList.noOfList");
        Sort sort = sort1.and(sort2).and(sort3);

        Pageable pageRequest = createPageRequest(pageNumber - 1, pageSize, sort);

        return songRepository.findAll(pageRequest);
    }

    public Page<Movie> pagableShowAllMovies(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Sort sort1 = new Sort(Sort.Direction.ASC, "title");
        Sort sort2 = new Sort(Sort.Direction.ASC, "firstTimeInList.noOfList");
        Sort sort = sort1.and(sort2);

        Pageable pageRequest = createPageRequest(pageNumber - 1, pageSize, sort);

        return movieRepository.findAll(pageRequest);
    }


    public Page<Artist> pagableShowAllArtists(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Sort sort1 = new Sort(Sort.Direction.ASC, "fullName");
        Sort sort2 = new Sort(Sort.Direction.ASC, "firstTimeInList.noOfList");
        Sort sort = sort1.and(sort2);

        Pageable pageRequest = createPageRequest(pageNumber - 1, pageSize, sort);

        return artistRepository.findAll(pageRequest);
    }

    public Page<LPMFPosition> pagableShowAllLists(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Sort sort1 = new Sort(Sort.Direction.DESC, "listInfo.noOfList");
        Sort sort2 = new Sort(Sort.Direction.ASC, "pos");
        Sort sort = sort1.and(sort2);

        Pageable pageRequest = createPageRequest(pageNumber - 1, pageSize, sort);

        return lpmfPositionRepository.findAll(pageRequest);

    }

    private Pageable createPageRequest(Integer pageNumber, Integer pageSize, Sort sort) {
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public Long listsTableSize() {
        return lpmfPositionRepository.count();
    }

    public Long artistsTableSize() {
        return artistRepository.count();
    }

    public Long songsTableSize() {
        return songRepository.count();
    }

    public Long moviesTableSize() {
        return movieRepository.count();
    }


}
