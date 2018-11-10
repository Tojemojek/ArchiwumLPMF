package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.RawLpmfData;

@Repository
public interface RawDataRepository extends JpaRepository<RawLpmfData, Integer> {

}
