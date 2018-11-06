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

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    ListInfoRepository listInfoRepository;

    @Autowired
    LPMFPositionRepository lpmfPositionRepository;

    public Map<Integer, ListInfo> persistListInfos(Set<ListInfo> listInfoS) {

        Map<Integer, ListInfo> ret = new HashMap<>();
        Set<ListInfo> all = new HashSet<>(listInfoRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all.size() + " listInfo");

        all.addAll(listInfoS);

        listInfoRepository.saveAll(listInfoS);

        for (ListInfo listInfo : all) {
            ret.put(listInfo.getNoOfList(),listInfo);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " listInfo");

        return ret;
    }


    public Map<String, Movie> persistMovies(Set<Movie> movieS) {

        Map<String,Movie> ret = new HashMap<>();
        Set<Movie> all = new HashSet<>(movieRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all.size() + " filmów");

        all.addAll(movieS);

        movieRepository.saveAll(movieS);

        for (Movie movie : all) {
            ret.put(movie.getTitle(),movie);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " filmów");

        return ret;
    }

    public Map<String, Artist> persistArtists(Set<Artist> artistS) {

        Map<String,Artist> ret = new HashMap<>();
        Set<Artist> all = new HashSet<>(artistRepository.findAll());

        LOG.debug("Przed BatchUpdate w bazie było " + all.size() + " artystów");

        all.addAll(artistS);

        artistRepository.saveAll(artistS);

        for (Artist artist : all) {
            ret.put(artist.getFullName() ,artist);
        }

        LOG.debug("Po BatchUpdate w bazie jest " + ret.size() + " artystów");

        return ret;
    }


}
