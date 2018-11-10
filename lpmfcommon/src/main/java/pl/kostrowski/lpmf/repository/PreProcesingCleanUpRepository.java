package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.PreProcesingCleanUpData;

import java.util.List;

@Repository
public interface CleanUpRepository extends CrudRepository<CleanUpData, Long> {

    List<CleanUpData> findCleanUpDataByCategory(String category);
}
