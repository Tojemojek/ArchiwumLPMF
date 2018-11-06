package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.RawLpmfData;

@Repository
public interface RawDataRepository extends CrudRepository<RawLpmfData, Integer> {

}
