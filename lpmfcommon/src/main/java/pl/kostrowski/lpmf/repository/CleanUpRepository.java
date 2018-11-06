package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.CleanUpData;

import java.util.List;

@Repository
public interface CleanUpRepository extends CrudRepository<CleanUpData, Long> {

    List<CleanUpData> findCleanUpDataByCategory(String category);
}
