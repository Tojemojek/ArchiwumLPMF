package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByFullNameContaining(String name);
}
