package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.kostrowski.lpmf.dto.ArtistSongsDto;
import pl.kostrowski.lpmf.dto.MovieSongsDto;
import pl.kostrowski.lpmf.dto.views.MedalTable;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.*;
import pl.kostrowski.lpmf.repository.views.ArtistMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.MovieMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.SongMedalTablesRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class DisplayAllService {

    private final LPMFPositionRepository lpmfPositionRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final MovieRepository movieRepository;

    private final ArtistMedalTablesRepository artistMedalTablesRepository;
    private final MovieMedalTablesRepository movieMedalTablesRepository;
    private final SongMedalTablesRepository songMedalTablesRepository;

    private final RawDataRepository rawDataRepository;

    @Autowired
    public DisplayAllService(LPMFPositionRepository lpmfPositionRepository, SongRepository songRepository, ArtistRepository artistRepository, MovieRepository movieRepository, ArtistMedalTablesRepository artistMedalTablesRepository, MovieMedalTablesRepository movieMedalTablesRepository, SongMedalTablesRepository songMedalTablesRepository, RawDataRepository rawDataRepository) {
        this.lpmfPositionRepository = lpmfPositionRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.movieRepository = movieRepository;
        this.artistMedalTablesRepository = artistMedalTablesRepository;
        this.movieMedalTablesRepository = movieMedalTablesRepository;
        this.songMedalTablesRepository = songMedalTablesRepository;
        this.rawDataRepository = rawDataRepository;
    }

    public List<LPMFPosition> findByNoOfList(Integer listNo) {
        return lpmfPositionRepository.findByNoOfListOrderByPos(listNo);
    }

    public List<LPMFPosition> customFindBySongId(Long songId, Model model) {
        List<LPMFPosition> byNoOfListOOrderByPos = lpmfPositionRepository.customFindBySongId(songId);

        if (byNoOfListOOrderByPos.get(0).getSong().getHasDuplicates() != null && byNoOfListOOrderByPos.get(0).getSong().getHasDuplicates()) {
            byNoOfListOOrderByPos = lpmfPositionRepository.
                    customFindBySongTitleAndMovieTitle(
                            byNoOfListOOrderByPos.get(0).getSong().getTitle(),
                            byNoOfListOOrderByPos.get(0).getSong().getMovie().getTitle());
        }

        MedalTable medalTable = songMedalTablesRepository.findFirstBySongTitle(byNoOfListOOrderByPos.get(0).getSong().getTitle());
        medalTable.prepare();

        model.addAttribute("medals", medalTable.getMedals());
        model.addAttribute("inLists", medalTable.getTotalInList());

        return byNoOfListOOrderByPos;
    }

    public ArtistSongsDto customFindArtistById(Long artistId, Model model) {

        Artist artist = artistRepository.findById(artistId).get();
        List<Song> songsByArtist = songRepository.customFindByArtistId(artistId);

        MedalTable medalTable = artistMedalTablesRepository.findFirstByArtistName(artist.getFullName());
        medalTable.prepare();

        model.addAttribute("medals", medalTable.getMedals());
        model.addAttribute("inLists", medalTable.getTotalInList());

        return new ArtistSongsDto(artist, songsByArtist);

    }

    public MovieSongsDto customFindMovieById(Long movieId, Model model) {

        Movie movie = movieRepository.findById(movieId).get();
        List<Song> songsByMovie = songRepository.customFindByMovieId(movieId);

        MedalTable medalTable = movieMedalTablesRepository.findFirstByMovieTitle(movie.getTitle());
        medalTable.prepare();

        model.addAttribute("medals", medalTable.getMedals());
        model.addAttribute("inLists", medalTable.getTotalInList());

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

    public List<List<LPMFPosition>> pagableShowAllLists(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Sort sort1 = new Sort(Sort.Direction.DESC, "listInfo.noOfList");
        Sort sort2 = new Sort(Sort.Direction.ASC, "pos");
        Sort sort = sort1.and(sort2);

        Pageable pageRequest = createPageRequest(pageNumber - 1, pageSize, sort);

        Page<LPMFPosition> all = lpmfPositionRepository.findAll(pageRequest);

        List<List<LPMFPosition>> listaList = new LinkedList<>();

        int i = 0;
        List<LPMFPosition> listaWew = new LinkedList<>();
        for (LPMFPosition lpmfPosition : all) {
            i++;
            listaWew.add(lpmfPosition);
            if (i == 20) {
                i = 0;
                listaList.add(listaWew);
                listaWew = new LinkedList<>();
            }
        }
        return listaList;
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


    public void prepareMain(Model model) {
        model.addAttribute("min",rawDataRepository.findMinList());
        model.addAttribute("max",rawDataRepository.findMaxList());
    }
}
