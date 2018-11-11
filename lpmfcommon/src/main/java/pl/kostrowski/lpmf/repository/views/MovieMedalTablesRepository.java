package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;

import java.util.LinkedList;

@Repository
public interface MovieMedalTablesRepository extends JpaRepository<MedalTableMovie, Long> {

    LinkedList<MedalTableMovie> findAll();
    Page<MedalTableMovie> findAll(Pageable pageable);
}
