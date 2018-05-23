package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.model.LPMFPosition;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@Repository
public interface LPMFPositionRepository extends CrudRepository<LPMFPosition, Long> {

    List<LPMFPosition> findAllByPos(Integer position);

    List<LPMFPosition> findByNoOfList(Integer noOfList);

    LPMFPosition findByNoOfListAndPos(Integer pos, Integer noOfList);

    @Query("SELECT lpmf FROM LPMFPosition lpmf left join lpmf.song as s where s.id = :songId")
    List<LPMFPosition> customFindBySong(@Param("songId") Long songId);


}
