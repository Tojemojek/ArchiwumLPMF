package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Artist;
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
}
