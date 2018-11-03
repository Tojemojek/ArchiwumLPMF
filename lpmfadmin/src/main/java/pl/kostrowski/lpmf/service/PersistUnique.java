package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PersistUnique {

    private static int newMoviesCount = 0;
    private static int newArtistCount = 0;
    private static int newSongCount = 0;
    private final Logger LOG = LoggerFactory.getLogger(PersistUnique.class);

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

    public static int getNewMoviesCount() {
        return newMoviesCount;
    }

    public static int getNewArtistCount() {
        return newArtistCount;
    }

    public static int getNewSongCount() {
        return newSongCount;
    }

    public Movie persistMovie(Movie movie) {

        Movie movieFromDB = null;

        try {
            movieFromDB = movieRepository.findMovieByTitle(movie.getTitle());
        } catch (Exception e) {

        }
        if (movieFromDB != null) {
            movie = movieFromDB;
        } else {
            movieRepository.save(movie);
            LOG.debug(movie + "dodano do bazy danych");
            newMoviesCount++;
        }

        return movie;
    }

    public Artist persistArtist(Artist artist) {

        Artist artistFromDB = null;

        try {
            artistFromDB = artistRepository.findArtistByFullName(artist.getFullName());
        } catch (Exception e) {

        }
        if (artistFromDB != null) {
            artist = artistFromDB;
        } else {
            artistRepository.save(artist);
            LOG.debug(artist + "dodano do bazy danych");
            newArtistCount++;
        }

        return artist;
    }

    public List<Artist> persistArtists(List<Artist> artists) {

        List<Artist> artistsFromDb = new LinkedList<>();

        for (Artist artist : artists) {
            artistsFromDb.add(persistArtist(artist));
        }
        return artistsFromDb;
    }

    public Song persistSong(Song song) {

        List<Song> songsFromDb = null;
        try {
            songsFromDb = songRepository.findAllByTitle(song.getTitle());
        } catch (Exception e) {
        }

        for (Song songFromDb : songsFromDb) {
            if (songFromDb != null && movieTitleAreSame(songFromDb, song) && twoAuthorsListsAreSame(songFromDb, song)) {
                song = songFromDb;
                return song;
            }
        }
        songRepository.save(song);
        LOG.debug(song + "dodano do bazy danych");
        newSongCount++;

        return song;
    }

    public void findDuplicatedSongs() {
        List<Object[]> zdublowane = songRepository.doubledSongs();
        List<Song> songsFromDb = new LinkedList<>();

        for (Object[] objects : zdublowane) {
            songsFromDb.addAll(songRepository.customfindAllByTitleAndMovieTitle((String) objects[0], (String) objects[1]));
        }

        for (Song song : songsFromDb) {
            song.setHasDuplicates(Boolean.TRUE);
            songRepository.saveAndFlush(song);
        }
    }


    public ListInfo persistListInfo(ListInfo listInfo) {

        ListInfo listInfoDb = null;

        try {
            listInfoDb = listInfoRepository.findByNoOfList(listInfo.getNoOfList());
        } catch (Exception e) {

        }
        if (listInfoDb != null) {
            listInfo = listInfoDb;
        } else {
            listInfoRepository.save(listInfo);
            LOG.debug(listInfo + "dodano do bazy danych");
        }

        return listInfo;
    }

    public LPMFPosition persistLPMFPosition(LPMFPosition lpmfPosition) {

        LPMFPosition lpmfPositionDb = null;

        try {
            lpmfPositionDb = lpmfPositionRepository.findByNoOfListAndPos(lpmfPosition.getNoOfList(), lpmfPosition.getPos());
        } catch (Exception e) {

        }
        if (lpmfPositionDb != null) {
            lpmfPosition = lpmfPositionDb;
        } else {
            lpmfPositionRepository.save(lpmfPosition);
            LOG.debug(lpmfPosition + "dodano do bazy danych");
        }
        return lpmfPosition;
    }

    private boolean twoAuthorsListsAreSame(Song fromDB, Song song) {
        List<Artist> authorsdb = fromDB.getAuthors();
        List<Artist> authors = song.getAuthors();

        if (authorsdb.size() != authors.size()) {
            return false;
        }

        for (Artist author : authors) {
            if (!authorsdb.contains(author)) {
                return false;
            }
        }
        return true;
    }

    private boolean movieTitleAreSame(Song fromDB, Song song) {

        String titleDb = fromDB.getMovie().getTitle();
        String title = song.getMovie().getTitle();

        return title.equals(titleDb);
    }

}
