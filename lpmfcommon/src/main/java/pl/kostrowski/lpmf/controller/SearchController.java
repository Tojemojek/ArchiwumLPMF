package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.dto.ArtistSongsDto;
import pl.kostrowski.lpmf.dto.MovieSongsDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.service.DisplayAllService;

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

        List<Song> allByTitleContaining = songRepository.findAllByTitleContaining(songTitle);

        model.addAttribute("songs", allByTitleContaining);
        return "search/song";
    }

    @RequestMapping(value = "/search/movies", method = RequestMethod.POST)
    public String showSearchMoviesResultsPage(Model model, @RequestParam(value = "movieTitle") String movieTitle) {

        List<Movie> allByTitleContaining = movieRepository.findAllByTitleContaining(movieTitle);

        model.addAttribute("movies", allByTitleContaining);
        return "search/movies";
    }

    @RequestMapping(value = "/search/artists", method = RequestMethod.POST)
    public String showSearchArtistsResultsPage(Model model, @RequestParam(value = "artistName") String artistName) {

        List<Artist> allByFullNameContaining = artistRepository.findAllByFullNameContaining(artistName);

        model.addAttribute("artists", allByFullNameContaining);
        return "search/artists";
    }


}
