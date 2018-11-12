package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.LPMFPosition;

import java.util.List;

@Repository
public interface LPMFPositionRepository extends JpaRepository<LPMFPosition, Long> {

    @Query(value = "SELECT lpmf FROM LPMFPosition lpmf left join lpmf.listInfo as listInfo where (listInfo.noOfList = :noOfList)")
    List<LPMFPosition> findByNoOfListOrderByPos(Integer noOfList);

    @Query(value = "SELECT lpmf FROM LPMFPosition lpmf left join lpmf.listInfo as listInfo where (lpmf.pos = :pos and listInfo.noOfList = :noOfList)")
    LPMFPosition findByNoOfListAndPos(@Param("noOfList")Integer noOfList, @Param("pos")Integer pos);

    @Query(value = "SELECT lpmf FROM LPMFPosition lpmf left join lpmf.song as s left join lpmf.listInfo as listInfo where s.id = :songId order by listInfo.noOfList")
    List<LPMFPosition> customFindBySongId(@Param("songId") Long songId);

    @Query(value = "SELECT lpmf FROM LPMFPosition lpmf left join lpmf.song as s left join s.movie as m left join lpmf.listInfo as listInfo where (s.title = :songTitle and m.title = :movieTitle) order by listInfo.noOfList")
    List<LPMFPosition> customFindBySongTitleAndMovieTitle(@Param("songTitle") String songTitle, @Param("movieTitle") String movieTitle);
}
