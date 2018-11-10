package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kostrowski.lpmf.dto.LPMFPositionWrapperDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.service.DisplayService;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class AllController {

    private final DisplayService displayService;

    @Autowired
    public AllController(DisplayService displayService) {
        this.displayService = displayService;
    }

    @RequestMapping(value = "/")
    public String displayMenu() {
        return "index";
    }

    @RequestMapping(value = "/all/songs")
    public String showAllSongs(Model model) {
        List<Song> allSongs = displayService.showAllSongs();
        model.addAttribute("allSongs", allSongs);
        return "all/songs";
    }

    @RequestMapping(value = "/all/movies")
    public String showAllMovies(Model model) {
        List<Movie> allMovies = displayService.showAllMovies();
        model.addAttribute("allMovies", allMovies);
        return "all/movies";
    }

    @RequestMapping(value = "/all/artists")
    public String showAllArtists(Model model) {
        List<Artist> allArtists = displayService.showAllArtists();
        model.addAttribute("allArtists", allArtists);
        return "all/artists";
    }

    @RequestMapping(value = "/all/lists")
    public String showAllLists(Model model) {
        List<LPMFPositionWrapperDto> allLists = displayService.showAllLists();
        model.addAttribute("allLists", allLists);
        return "all/lists";
    }

}
