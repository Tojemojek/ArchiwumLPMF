package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.PreProcesingCleanUpData;

import java.util.List;

@Repository
public interface PreProcesingCleanUpRepository extends JpaRepository<PreProcesingCleanUpData, Long> {

    List<PreProcesingCleanUpData> findCleanUpDataByCategory(String category);


}
