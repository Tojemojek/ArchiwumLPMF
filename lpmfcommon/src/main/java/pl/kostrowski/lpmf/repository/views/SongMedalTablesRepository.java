package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;

import java.util.LinkedList;

@Repository
public interface SongMedalTablesRepository extends JpaRepository<MedalTableSong, String> {

    LinkedList<MedalTableSong> findAll();
}
