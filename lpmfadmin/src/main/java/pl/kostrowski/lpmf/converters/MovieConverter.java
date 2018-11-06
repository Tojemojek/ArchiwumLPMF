package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dictionaries.AllDictionaries;
import pl.kostrowski.lpmf.dto.SingleLpmfDto;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Movie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MovieConverter {

    public Movie convert(String singleEntryInListDtos, ListInfo listInfo) {

        Map<String, String> moviesDictionary = AllDictionaries.getMoviesDictionary();

        Movie movie = new Movie();
        String title = singleEntryInListDtos;

        if (moviesDictionary.containsKey(title)) {
            title = moviesDictionary.get(title);
        }
        movie.setTitle(title);
        movie.setFirstTimeInList(listInfo);

        return movie;
    }

}
