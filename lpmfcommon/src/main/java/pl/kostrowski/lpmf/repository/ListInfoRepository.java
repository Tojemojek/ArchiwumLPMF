package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.ListInfo;

@Repository
public interface ListInfoRepository extends JpaRepository<ListInfo, Integer> {

}
