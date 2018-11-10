package pl.kostrowski.lpmf.repository.views;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;

import java.util.LinkedList;

@Repository
public interface ArtistMedalTablesRepository extends JpaRepository<MedalTableArtist, Long> {

    LinkedList<MedalTableArtist> findAll();

}
