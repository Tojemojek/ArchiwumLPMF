package pl.kostrowski.lpmf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Movie findMovieByTitle(String title);
    List<Movie> findAll();
}
