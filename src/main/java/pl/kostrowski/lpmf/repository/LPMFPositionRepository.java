package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.LPMFPosition;

import java.util.List;

@Repository
public interface LPMFPositionRepository extends CrudRepository<LPMFPosition, Long> {

    List<LPMFPosition> findAllByPos(Integer position);

    List<LPMFPosition> findByNoOfList(Integer noOfList);

    LPMFPosition findByNoOfListAndPos(Integer pos, Integer noOfList);

}
