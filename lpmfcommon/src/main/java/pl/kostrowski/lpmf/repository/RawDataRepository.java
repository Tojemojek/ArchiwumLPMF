package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.RawLpmfData;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RawDataRepository extends JpaRepository<RawLpmfData, Integer> {

    @Query(nativeQuery = true, value="SELECT min(raw_lpmf_data.id) FROM raw_lpmf_data")
    Object findMinList();

    @Query(nativeQuery = true, value="SELECT max(raw_lpmf_data.id) FROM raw_lpmf_data")
    Object findMaxList();
}
