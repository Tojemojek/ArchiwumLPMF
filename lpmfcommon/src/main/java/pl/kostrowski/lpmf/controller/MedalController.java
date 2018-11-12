package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;
import pl.kostrowski.lpmf.service.DisplayMedalTables;
import pl.kostrowski.lpmf.service.DisplayServiceUtil;

@SuppressWarnings("SameReturnValue")
@Controller
public class MedalController {

    private final int PAGE_SIZE = 20;

    private final DisplayMedalTables displayMedalTables;
    private final DisplayServiceUtil displayServiceUtil;

    @Autowired
    public MedalController(DisplayMedalTables displayMedalTables, DisplayServiceUtil displayServiceUtil) {
        this.displayMedalTables = displayMedalTables;
        this.displayServiceUtil = displayServiceUtil;
    }

    @RequestMapping(value = "/medals/songs")
    public String showSongsMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayMedalTables.medalTableSongsSize();
        Page<MedalTableSong> allMedalSongs = displayMedalTables.pagebleMedalTableSongs(pageNo, PAGE_SIZE);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalSongs", allMedalSongs);

        return "medal/songs";
    }

    @RequestMapping(value = "/medals/artists")
    public String showArtisMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayMedalTables.medalTableArtistsSize();
        Page<MedalTableArtist> allMedalArtists = displayMedalTables.pagebleMedalTableArtists(pageNo, PAGE_SIZE);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalArtists", allMedalArtists);

        return "medal/artists";
    }

    @RequestMapping(value = "/medals/movies")
    public String showMoviesMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = displayMedalTables.medalTableMoviesSize();
        Page<MedalTableMovie> allMedalMovies = displayMedalTables.pagebleMedalTableMovies(pageNo, PAGE_SIZE);

        displayServiceUtil.preparePaging(model, pageNo, size, PAGE_SIZE);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalMovies", allMedalMovies);
        return "medal/movies";
    }

}
