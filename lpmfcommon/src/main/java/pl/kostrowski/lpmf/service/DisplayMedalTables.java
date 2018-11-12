package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;
import pl.kostrowski.lpmf.repository.views.ArtistMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.MovieMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.SongMedalTablesRepository;


@Service
public class DisplayMedalTables {


    private final ArtistMedalTablesRepository artistMedalTablesRepository;
    private final MovieMedalTablesRepository movieMedalTablesRepository;
    private final SongMedalTablesRepository songMedalTablesRepository;

    @Autowired
    public DisplayMedalTables(ArtistMedalTablesRepository artistMedalTablesRepository, MovieMedalTablesRepository movieMedalTablesRepository, SongMedalTablesRepository songMedalTablesRepository) {
        this.artistMedalTablesRepository = artistMedalTablesRepository;
        this.movieMedalTablesRepository = movieMedalTablesRepository;
        this.songMedalTablesRepository = songMedalTablesRepository;
    }

    public Page<MedalTableArtist> pagebleMedalTableArtists(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Pageable pageRequest = createPageRequest(pageNumber-1, pageSize);
        Page<MedalTableArtist> ret = artistMedalTablesRepository.findAll(pageRequest);

        for (MedalTableArtist medalTableArtist : ret) {
            medalTableArtist.prepare();
        }

        return ret;
    }


    public Page<MedalTableMovie> pagebleMedalTableMovies(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Pageable pageRequest = createPageRequest(pageNumber-1, pageSize);
        Page<MedalTableMovie> ret = movieMedalTablesRepository.findAll(pageRequest);

        for (MedalTableMovie medalTableMovie : ret) {
            medalTableMovie.prepare();
        }

        return ret;
    }



    public Page<MedalTableSong> pagebleMedalTableSongs(Integer pageNumber, Integer pageSize) {

        if (pageNumber < 1) {
            pageNumber = 1;
        }

        Pageable pageRequest = createPageRequest(pageNumber-1, pageSize);
        Page<MedalTableSong> ret = songMedalTablesRepository.findAll(pageRequest);

        for (MedalTableSong MedalTableSong : ret) {
            MedalTableSong.prepare();
        }

        return ret;
    }

    public Long medalTableSongsSize() {
        return songMedalTablesRepository.count();
    }

    public Long medalTableArtistsSize() {
        return artistMedalTablesRepository.count();
    }

    public Long medalTableMoviesSize() {
        return movieMedalTablesRepository.count();
    }


    private Pageable createPageRequest(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }

}
