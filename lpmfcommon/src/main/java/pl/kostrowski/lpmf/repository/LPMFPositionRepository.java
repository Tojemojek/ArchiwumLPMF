package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.LPMFPosition;

import java.util.List;

@Repository
public interface LPMFPositionRepository extends CrudRepository<LPMFPosition, Long> {

    List<LPMFPosition> findAllByPos(Integer position);

    List<LPMFPosition> findAll();

    List<LPMFPosition> findByNoOfListOrderByPos(Integer noOfList);

    LPMFPosition findByNoOfListAndPos(Integer pos, Integer noOfList);

    @Query("SELECT lpmf FROM LPMFPosition lpmf left join lpmf.song as s where s.id = :songId order by lpmf.noOfList")
    List<LPMFPosition> customFindBySongId(@Param("songId") Long songId);

    @Query("SELECT lpmf FROM LPMFPosition lpmf left join lpmf.song as s left join s.movie as m where (s.title = :songTitle and m.title = :movieTitle) order by lpmf.noOfList")
    List<LPMFPosition> customFindBySongTitleAndMovieTitle(@Param("songTitle") String songTitle, @Param("movieTitle") String movieTitle);
}
