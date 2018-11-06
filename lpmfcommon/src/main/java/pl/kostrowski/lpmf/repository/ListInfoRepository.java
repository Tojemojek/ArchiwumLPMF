package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.ListInfo;

import java.util.List;

@Repository
public interface ListInfoRepository extends CrudRepository<ListInfo, Integer> {

    ListInfo findByNoOfList(Integer noOfList);
    List<ListInfo> findAll();
}
