package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.service.DisplayAllService;
import pl.kostrowski.lpmf.service.DisplayServiceUtil;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class AllController {

    private final int PAGE_SIZE = 20;

    private final DisplayAllService displayAllService;
    private final DisplayServiceUtil displayServiceUtil;

    @Autowired
    public AllController(DisplayAllService displayAllService, DisplayServiceUtil displayServiceUtil) {
        this.displayAllService = displayAllService;
        this.displayServiceUtil = displayServiceUtil;
    }

    @RequestMapping(value = "/")
    public String displayMenu(Model model) {

        displayAllService.prepareMain(model);

        return "index";
    }

    @RequestMapping(value = "/all/songs")
    public String showAllSongs(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayAllService.songsTableSize();
        Page<Song> allSongs = displayAllService.pagableShowAllSongs(pageNo, PAGE_SIZE * 5);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE * 5);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allSongs", allSongs);
        return "all/songs";
    }

    @RequestMapping(value = "/all/movies")
    public String showAllMovies(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayAllService.moviesTableSize();
        Page<Movie> allMovies = displayAllService.pagableShowAllMovies(pageNo, PAGE_SIZE * 5);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE * 5);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMovies", allMovies);

        return "all/movies";
    }

    @RequestMapping(value = "/all/artists")
    public String showAllArtists(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayAllService.artistsTableSize();
        Page<Artist> allArtists = displayAllService.pagableShowAllArtists(pageNo, PAGE_SIZE * 5);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE * 5);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allArtists", allArtists);

        return "all/artists";
    }

    @RequestMapping(value = "/all/lists")
    public String showAllLists(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayAllService.listsTableSize();
        List<List<LPMFPosition>> allLists = displayAllService.pagableShowAllLists(pageNo, PAGE_SIZE * 10);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE * 10);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allLists", allLists);

        return "all/lists.html";
    }

}
