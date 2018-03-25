package pl.kostrowski.lpmf;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.converters.FilmTitleConverter;
import pl.kostrowski.lpmf.dto.SingleSongInListDto;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.model.Movie;

import pl.kostrowski.lpmf.service.PersistUnique;

@Service
public class Starter implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(Starter.class);

	@Autowired
	JsoupFileParser jsfp;

	@Autowired
	FilmTitleConverter ftc;

	@Autowired
	PersistUnique pu;

	@Value("${my.max.list}")
	private int maxList;

	@Override
	public void run(String... arg0) throws Exception {

		List<SingleSongInListDto> ssinli = new LinkedList<>();
		List<Movie> movies = new LinkedList<>();
		int newMoviesBefore = 0;
		int newMoviesAfter = 0;
		int deltaMovies = 0;

		for (int i = 0; i <= maxList; i++) {
			ssinli = jsfp.makeDOMfor(i);
			movies = ftc.convert(ssinli);

			newMoviesBefore = PersistUnique.getNewMoviesCount();

			for (Movie movie : movies) {
				if (movie.getTitle().equals("Star Trek")) {
					LOG.error("Błąd w liście "+ i );
				}
				pu.persistMovie(movie);
			}
			newMoviesAfter = PersistUnique.getNewMoviesCount();
			deltaMovies = newMoviesAfter - newMoviesBefore;

			LOG.info("W pliku nr: " + i + " nowych filmów jest: " + deltaMovies);

		}

	}

}
