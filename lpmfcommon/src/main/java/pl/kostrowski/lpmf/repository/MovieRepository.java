package pl.kostrowski.lpmf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
