package pl.kostrowski.lpmf.converters;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.dictionaries.AllDictionaries;

@Service
public class FilmTitleConverter {

    public List<Movie> convert(List<SingleEntryInListDto> singleEntryInListDtos) {

        Map<String, String> moviesDictionary = AllDictionaries.getMoviesDictionary();

        List<Movie> movies = new LinkedList<>();
        String listNumberAndDate;


        for (SingleEntryInListDto singleEntryInListDto : singleEntryInListDtos) {
            Movie movie = new Movie();
            String title = singleEntryInListDto.getFullMovieTitle();
            listNumberAndDate = singleEntryInListDto.getNrAndDateOfList();

            if (moviesDictionary.containsKey(title)) {
                title = moviesDictionary.get(movie.getTitle());
            }
            movie.setTitle(title);
            movie.setFirstTimeInList(listNumberAndDate);
            movies.add(movie);
        }
        return movies;
    }

}
