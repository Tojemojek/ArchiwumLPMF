package pl.kostrowski.lpmf.converters;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.dto.SingleSongInListDto;
import pl.kostrowski.lpmf.model.Movie;

@Service
public class FilmTitleConverter {
	
	public List<Movie> convert(List<SingleSongInListDto> ssinli) {
		
		
		List<Movie> movies = new LinkedList<>();
		
		for (SingleSongInListDto song :ssinli) {
			Movie movie = new Movie();
			movie.setTitle(song.getFullMovieTitle());
			movies.add(movie);
		}
		return movies;
	}

}
