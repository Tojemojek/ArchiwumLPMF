package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;

import java.util.LinkedList;

@Repository
public interface ArtistMedalTablesRepository extends JpaRepository<MedalTableArtist, Long> {

    LinkedList<MedalTableArtist> findAll();
    Page<MedalTableArtist> findAll(Pageable pageable);
}
