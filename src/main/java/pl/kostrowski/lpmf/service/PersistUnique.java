package pl.kostrowski.lpmf.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.dictionaries.AllDictionaries;
import pl.kostrowski.lpmf.repository.MovieRepository;

@Service
public class PersistUnique {

	private final Logger LOG = LoggerFactory.getLogger(PersistUnique.class);
	private static int newMoviesCount = 0;
	
	@Autowired
	MovieRepository mr;

	public Movie persistMovie(Movie movie) {

		Map<String,String> moviesDictionary = AllDictionaries.getMoviesDictionary();
				
		Movie movieFromDB = null;
		
		if (moviesDictionary.containsKey(movie.getTitle())) {
			movie.setTitle(moviesDictionary.get(movie.getTitle()));
		}
		
		try {
			movieFromDB = mr.findMovieByTitle(movie.getTitle());
		} catch (Exception e) {

		}
		if (movieFromDB != null) {
			movie = movieFromDB;
		} else {
			mr.save(movie);
			LOG.debug(movie + "dodano do bazy danych");
			newMoviesCount++;
		}

		return movie;
	}

	public static int getNewMoviesCount() {
		return newMoviesCount;
	}


}
