package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.dictionaries.AllDictionaries;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MovieConverter {

    public List<Movie> convertOLD(List<SingleEntryInListDto> singleEntryInListDtos) {

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
//            movie.setFirstTimeInList(listNumberAndDate);
            movies.add(movie);
        }
        return movies;
    }

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
