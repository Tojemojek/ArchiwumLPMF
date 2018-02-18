package OLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.repository.MovieRepository;

@Component
public class ParseMovie {

    private MovieRepository movieRepository;

    @Autowired
    public ParseMovie(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie makeMovie(String movieTitle) {

        Movie movie = new Movie();
        Movie movieFromRepository;

        try {
            movieFromRepository = movieRepository.findMovieByTitle(movieTitle);
        } catch (Exception e) {
            movieFromRepository = null;
        }

        if (movieFromRepository != null) {
            movie.setId(movieFromRepository.getId());
            movie.setTitle(movieTitle);
        } else {
            movie.setTitle(movieTitle);
            movieRepository.save(movie);
        }
        return movie;
    }

}
