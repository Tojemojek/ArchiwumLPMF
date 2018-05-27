package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    Song findSongByTitleAndMovieAndAuthors (String title, Movie movie, List<Artist> authors);
    Song findSongByTitleAndMovie (String title, Movie movie);
    Song findSongByTitle (String title);
    List<Song> findAllByTitle (String title);
    List<Song> findAll ();


    @Query("SELECT s from Song s left join s.authors a where a.id = :artistId")
    List<Song> customFindByArtistId(@Param("artistId") Long artistId);

    @Query("SELECT s from Song s left join s.movie m where m.id = :movieId")
    List<Song> customFindByMovieId(@Param("movieId") Long movieId);
}
