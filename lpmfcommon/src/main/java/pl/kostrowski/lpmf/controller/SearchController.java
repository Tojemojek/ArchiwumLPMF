package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class SearchController {

    @Autowired
    SongRepository songRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ArtistRepository artistRepository;

    @RequestMapping(value = "/search")
    public String showSearchPage() {
        return "search/general";
    }

    @RequestMapping(value = "/search/songs", method = RequestMethod.POST)
    public String showSearchSongsResultsPage(Model model, @RequestParam(value = "songTitle") String songTitle) {

        List<Song> allByTitleContaining = songRepository.findAllByTitleContainingOrderByTitle(songTitle);

        if (searchTermTooShort(model, songTitle, "songs") ||
                processNotFound(model, songTitle, allByTitleContaining, "songs")) {
            return "search/general";
        }

        model.addAttribute("songs", allByTitleContaining);
        return "search/song";
    }

    @RequestMapping(value = "/search/movies", method = RequestMethod.POST)
    public String showSearchMoviesResultsPage(Model model, @RequestParam(value = "movieTitle") String movieTitle) {

        List<Movie> allByTitleContaining = movieRepository.findAllByTitleContainingOrderByTitle(movieTitle);

        if (searchTermTooShort(model, movieTitle, "movies") ||
                processNotFound(model, movieTitle, allByTitleContaining, "movies")) {
            return "search/general";
        }

        model.addAttribute("movies", allByTitleContaining);
        return "search/movies";
    }

    @RequestMapping(value = "/search/artists", method = RequestMethod.POST)
    public String showSearchArtistsResultsPage(Model model, @RequestParam(value = "artistName") String artistName) {

        List<Artist> allByFullNameContaining = artistRepository.findAllByFullNameContainingOrderByFullName(artistName);

        if (searchTermTooShort(model, artistName, "artists") ||
                processNotFound(model, artistName, allByFullNameContaining, "artists")) {
            return "search/general";
        }

        model.addAttribute("artists", allByFullNameContaining);
        return "search/artists";
    }

    private boolean processNotFound(Model model, String searchString, List searchResults, String type) {
        String attributeMessageKey = type + "Message";
        String attributeMessageValue = "general.search.page.message." + type;
        String searchedString = type + "Searched";

        if (searchResults == null || searchResults.size() < 1) {
            model.addAttribute(attributeMessageKey, attributeMessageValue);
            model.addAttribute(searchedString, searchString);
            return true;
        }

        return false;
    }

    private boolean searchTermTooShort(Model model, String searchString, String type) {
        String attributeMessageKey = type + "Message";
        String attributeMessageValue = "general.search.page.too.short";
        String searchedString = type + "Searched";

        if (searchString == null || searchString.length() < 3) {
            model.addAttribute(attributeMessageKey, attributeMessageValue);
            model.addAttribute(searchedString, searchString);
            return true;
        }
        return false;
    }


}
