package pl.kostrowski.lpmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;
import pl.kostrowski.lpmf.repository.views.ArtistMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.MovieMedalTablesRepository;
import pl.kostrowski.lpmf.repository.views.SongMedalTablesRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class MedalTableDisplay {


    private final ArtistMedalTablesRepository artistMedalTablesRepository;
    private final MovieMedalTablesRepository movieMedalTablesRepository;
    private final SongMedalTablesRepository songMedalTablesRepository;

    @Autowired
    public MedalTableDisplay(ArtistMedalTablesRepository artistMedalTablesRepository, MovieMedalTablesRepository movieMedalTablesRepository, SongMedalTablesRepository songMedalTablesRepository) {
        this.artistMedalTablesRepository = artistMedalTablesRepository;
        this.movieMedalTablesRepository = movieMedalTablesRepository;
        this.songMedalTablesRepository = songMedalTablesRepository;
    }

    public List<MedalTableArtist> medalTableArtists() {

        LinkedList<MedalTableArtist> all = artistMedalTablesRepository.findAll();

        for (MedalTableArtist medalTableArtist : all) {
            medalTableArtist.prepare();
        }

        return all;
    }


    public List<MedalTableMovie> medalTableMovies() {

        LinkedList<MedalTableMovie> all = movieMedalTablesRepository.findAll();

        for (MedalTableMovie MedalTableMovie : all) {
            MedalTableMovie.prepare();
        }

        return all;
    }

    public List<MedalTableSong> medalTableSongs() {

        LinkedList<MedalTableSong> all = songMedalTablesRepository.findAll();

        for (MedalTableSong MedalTableSong : all) {
            MedalTableSong.prepare();
        }

        return all;
    }

}
