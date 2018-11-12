package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;

@Repository
public interface SongMedalTablesRepository extends JpaRepository<MedalTableSong, String> {

    Page<MedalTableSong> findAll(Pageable pageable);
}