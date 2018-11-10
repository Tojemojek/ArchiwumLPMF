package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;

import java.util.LinkedList;

@Repository
public interface MovieMedalTablesRepository extends JpaRepository<MedalTableMovie, Long> {

    LinkedList<MedalTableMovie> findAll();
}
