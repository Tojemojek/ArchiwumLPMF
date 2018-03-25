package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.SongInList;

@Repository
public interface SongInListRepository extends CrudRepository<SongInList, String> {


}
