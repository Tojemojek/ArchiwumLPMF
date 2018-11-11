package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.*;
import pl.kostrowski.lpmf.repository.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PersistUniqueBatch {

    private final int MAX_BATCH_SIZE = 100;

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


    @Transactional
    public Map<Integer, ListInfo> persistListInfos(Set<ListInfo> listInfoS) {

        Set<ListInfo> fromDb = new HashSet<>(listInfoRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + fromDb.size() + " listInfo");

        for (ListInfo listInfo : fromDb) {
            listInfoS.remove(listInfo);
        }

        LOG.debug("Zapisuję klasę ListInfo");
        saveBatch(listInfoS, listInfoRepository);

        List<ListInfo> all = listInfoRepository.findAll();

        Map<Integer, ListInfo> ret = new HashMap<>();
        for (ListInfo listInfo : all) {
            ret.put(listInfo.getNoOfList(), listInfo);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " listInfo");

        return ret;
    }

    @Transactional
    public Map<String, Movie> persistMovies(Set<Movie> movieS) {

        Set<Movie> fromDb = new HashSet<>(movieRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + fromDb.size() + " filmów");

        for (Movie movie : fromDb) {
            movieS.remove(movie);
        }

        LOG.debug("Zapisuję klasę Movie");
        saveBatch(movieS, movieRepository);

        List<Movie> all = movieRepository.findAll();

        Map<String, Movie> ret = new HashMap<>();
        for (Movie movie : all) {
            ret.put(movie.toString(), movie);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " filmów");

        return ret;
    }

    @Transactional
    public Map<String, Artist> persistArtists(Set<Artist> artistS) {

        Set<Artist> fromDb = new HashSet<>(artistRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + fromDb.size() + " artystów");

        for (Artist artist : fromDb) {
            artistS.remove(artist);
        }

        LOG.debug("Zapisuję klasę Artist");
        saveBatch(artistS, artistRepository);

        List<Artist> all = artistRepository.findAll();

        Map<String, Artist> ret = new HashMap<>();
        for (Artist artist : all) {
            ret.put(artist.toString(), artist);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " artystów");

        return ret;
    }

    @Transactional
    public Map<String, Song> persistSongs(Set<Song> songS) {

       Set<Song> fromDb = new HashSet<>(songRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + fromDb.size() + " utworów");

        for (Song song : fromDb) {
            songS.remove(song);
        }

        LOG.debug("Zapisuję klasę Song");
        saveBatch(songS, songRepository);

        List<Song> all = songRepository.findAll();

        Map<String, Song> ret = new HashMap<>();
        for (Song song : all) {
            ret.put(song.toString(), song);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " utworów");

        return ret;

    }

    @Transactional
    public void persistLPMFPosition(Set<LPMFPosition> lpmfPositionS) {

        Set<LPMFPosition> fromDb = new HashSet<>(lpmfPositionRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + fromDb.size() + " pozycji");

        for (LPMFPosition lpmfPosition : fromDb) {
            lpmfPositionS.remove(lpmfPosition);
        }

        LOG.debug("Zapisuję klasę Song");
        saveBatch(lpmfPositionS, lpmfPositionRepository);

        long count = lpmfPositionRepository.count();

        LOG.debug("Po BatchUpdate w bazie jest " + count + " pozycji");

    }


    public <T> void saveBatch(Set<T> listToSave, JpaRepository<T, ? extends Object> jpaRepository) {

        if (!(listToSave.size() > 0)){
            return;
        }

        List<T> tmpList = new LinkedList<>();
        int size = listToSave.size();
        int i = 0;
        int j = 0;

        LOG.debug("Do zapisania jest " + listToSave.size() + " elementów");

        for (T t : listToSave) {
            i++;
            j++;
            tmpList.add(t);
            if (i == MAX_BATCH_SIZE){
                jpaRepository.saveAll(tmpList);
                jpaRepository.flush();
                tmpList.clear();
                i=0;
                LOG.debug("Zapisano " + j + " z " + size + " czyli " + ((j*1.0/size*1.0)*100) + "%");
            }
        }
        jpaRepository.saveAll(tmpList);
        jpaRepository.flush();
        listToSave.clear();
        LOG.debug("Zapisano " + j + " z " + size + " czyli " + ((j*1.0/size*1.0)*100) + "%");
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
