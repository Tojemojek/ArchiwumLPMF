package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.LPMFPosition;

@Repository
public interface SongInListRepository extends JpaRepository<LPMFPosition, Long> {


}
