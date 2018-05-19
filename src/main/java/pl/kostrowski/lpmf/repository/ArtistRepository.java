package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    Artist findArtistByFullName(String fullName);

}
