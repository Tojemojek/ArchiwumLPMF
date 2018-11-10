package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.*;
import pl.kostrowski.lpmf.repository.*;

import java.util.*;

@Service
public class PersistUniqueBatch {

    private final Logger LOG = LoggerFactory.getLogger(PersistUniqueBatch.class);

    private final MovieRepository movieRepository;

    private final ArtistRepository artistRepository;

    private final SongRepository songRepository;

    private final ListInfoRepository listInfoRepository;

    private final LPMFPositionRepository lpmfPositionRepository;

    @Autowired
    public PersistUniqueBatch(MovieRepository movieRepository, ArtistRepository artistRepository, SongRepository songRepository, ListInfoRepository listInfoRepository, LPMFPositionRepository lpmfPositionRepository) {
        this.movieRepository = movieRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.listInfoRepository = listInfoRepository;
        this.lpmfPositionRepository = lpmfPositionRepository;
    }


    public Map<Integer, ListInfo> persistListInfos(Set<ListInfo> listInfoS) {

        Map<Integer, ListInfo> ret = new HashMap<>();
        Set<ListInfo> all1 = new HashSet<>(listInfoRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all1.size() + " listInfo");

        all1.addAll(listInfoS);

        for (ListInfo listInfo : all1) {
            listInfoRepository.saveAndFlush(listInfo);
        }
        listInfoRepository.flush();

        List<ListInfo> all = listInfoRepository.findAll();

        for (ListInfo listInfo : all) {
            ret.put(listInfo.getNoOfList(), listInfo);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " listInfo");

        return ret;
    }

    public Map<String, Movie> persistMovies(Set<Movie> movieS) {

        Map<String, Movie> ret = new HashMap<>();
        Set<Movie> all1 = new HashSet<>(movieRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all1.size() + " filmów");

        all1.addAll(movieS);

        for (Movie movie : all1) {
            movieRepository.save(movie);
        }

        movieRepository.flush();

        List<Movie> all = movieRepository.findAll();

        for (Movie movie : all) {
            ret.put(movie.toString(), movie);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " filmów");

        return ret;
    }

    public Map<String, Artist> persistArtists(Set<Artist> artistS) {

        Map<String, Artist> ret = new HashMap<>();
        Set<Artist> all1 = new HashSet<>(artistRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all1.size() + " artystów");

        all1.addAll(artistS);

        for (Artist artist : all1) {
            artistRepository.save(artist);
        }
        artistRepository.flush();

        List<Artist> all = artistRepository.findAll();

        for (Artist artist : all) {
            ret.put(artist.toString(), artist);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " artystów");

        return ret;
    }

    public Map<String, Song> persistSongs(Set<Song> songS) {

        Map<String, Song> ret = new HashMap<>();
        Set<Song> all1 = new HashSet<>(songRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all1.size() + " utworów");

        all1.addAll(songS);

        for (Song song : all1) {
            songRepository.save(song);
        }
        songRepository.flush();

        List<Song> all = songRepository.findAll();


        for (Song song : all) {
            ret.put(song.toString(), song);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " utworów");

        return ret;

    }

    public void persistLPMFPosition(Set<LPMFPosition> lpmfPositionS) {

        Set<LPMFPosition> all1 = new HashSet<>(lpmfPositionRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all1.size() + " pozycji");

        all1.addAll(lpmfPositionS);
        for (LPMFPosition lpmfPosition : all1) {
            lpmfPositionRepository.save(lpmfPosition);
        }
        lpmfPositionRepository.flush();

        long count = lpmfPositionRepository.count();

        LOG.debug("Po BatchUpdate w bazie jest " + count + " pozycji");

    }

    public void findDuplicatedSongs() {
        List<Object[]> zdublowane = songRepository.doubledSongs();
        List<Song> songsFromDb = new LinkedList<>();

        for (Object[] objects : zdublowane) {
            songsFromDb.addAll(songRepository.customfindAllByTitleAndMovieTitle((String) objects[0], (String) objects[1]));
        }

        for (Song song : songsFromDb) {
            song.setHasDuplicates(Boolean.TRUE);
            songRepository.save(song);
        }
        songRepository.flush();
    }

}
