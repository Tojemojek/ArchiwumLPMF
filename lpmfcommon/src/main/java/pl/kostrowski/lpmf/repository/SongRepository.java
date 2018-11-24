package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = "SELECT s from Song s left join s.artists a where a.id = :artistId")
    List<Song> customFindByArtistId(@Param("artistId") Long artistId);

    @Query(value = "SELECT s from Song s left join s.movie m where m.id = :movieId")
    List<Song> customFindByMovieId(@Param("movieId") Long movieId);

    @Query(value = "SELECT s from Song s left join s.movie m where s.title=:songTitle and m.title = :movieTitle")
    List<Song> customfindAllByTitleAndMovieTitle(@Param("songTitle") String songTitle,@Param("movieTitle") String movieTitle);


    @Query(nativeQuery=true, value = "select total.songTitle, total.movieTitle  from " +
            "(select s.title as songTitle, m.title as movieTitle, s.id as sid, count(concat(s.title, m.title)) as zliczenie " +
            "from song s left join movie m on m.id = s.movie_id " +
            "group by concat(s.title, m.title)) " +
            "total where  total.zliczenie > 1")
    List<Object[]> doubledSongs();

    List<Song> findAllByTitleContaining(String title);

}
