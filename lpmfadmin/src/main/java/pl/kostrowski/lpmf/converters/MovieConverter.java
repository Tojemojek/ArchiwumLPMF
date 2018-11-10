package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Movie;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieConverter {

    private Map<String, String> moviesDictionary = new HashMap<>();

    public Movie convert(String singleEntryInListDtos, ListInfo listInfo) {

        Movie movie = new Movie();
        String title = singleEntryInListDtos;

        if (moviesDictionary.containsKey(title)) {
            title = moviesDictionary.get(title);
        }
        movie.setTitle(title);
        movie.setFirstTimeInList(listInfo);

        return movie;
    }

    public void setMoviesDictionary(Map<String, String> moviesDictionary) {
        this.moviesDictionary = moviesDictionary;
    }
}
