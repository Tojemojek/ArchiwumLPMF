package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.FullList;

@Repository
public interface FullListRepository extends CrudRepository<FullList, String> {

}
