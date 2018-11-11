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
import pl.kostrowski.lpmf.service.MedalTableDisplay;

@SuppressWarnings("SameReturnValue")
@Controller
public class MedalController {

    final private MedalTableDisplay medalTableDisplay;
    final private int PAGE_SIZE = 20;

    @Autowired
    public MedalController(MedalTableDisplay medalTableDisplay) {
        this.medalTableDisplay = medalTableDisplay;
    }

    @RequestMapping(value = "/medals/songs")
    public String showSongsMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = medalTableDisplay.medalTableSongsSize();
        Page<MedalTableSong> allMedalSongs = medalTableDisplay.pagebleMedalTableSongs(pageNo, PAGE_SIZE);

        preparePaging(model, pageNo, size);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalSongs", allMedalSongs);

        return "medal/songs";
    }

    @RequestMapping(value = "/medals/artists")
    public String showArtisMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = medalTableDisplay.medalTableArtistsSize();
        Page<MedalTableArtist> allMedalArtists = medalTableDisplay.pagebleMedalTableArtists(pageNo, PAGE_SIZE);

        preparePaging(model, pageNo, size);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalArtists", allMedalArtists);

        return "medal/artists";
    }

    @RequestMapping(value = "/medals/movies")
    public String showMoviesMedals(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        Long size = medalTableDisplay.medalTableMoviesSize();
        Page<MedalTableMovie> allMedalMovies = medalTableDisplay.pagebleMedalTableMovies(pageNo, PAGE_SIZE);

        preparePaging(model, pageNo, size);

        model.addAttribute("pageSize", PAGE_SIZE);
        model.addAttribute("allMedalMovies", allMedalMovies);
        return "medal/movies";
    }


    private void preparePaging(Model model, Integer pageNo, Long size) {
        long totalPages = 0;
        if (size % PAGE_SIZE ==0){
            totalPages = size / PAGE_SIZE;
        } else {
            totalPages = size / PAGE_SIZE + 1;
        }


        long[] fromTo = new long[2];
        boolean[] displayPreviousNext = new boolean[2];

        if (pageNo > 5 && totalPages - pageNo > 5) {
            fromTo[0] = pageNo - 5;
            fromTo[1] = pageNo + 5;
        } else if (pageNo <= 5) {
            fromTo[0] = 1;
            fromTo[1] = 10;
        } else if (totalPages - pageNo <= 5) {
            fromTo[0] = totalPages - 10;
            fromTo[1] = totalPages;
        }

        if (pageNo != 1){
            displayPreviousNext[0] = true;
        } else {
            displayPreviousNext[0] = false;
        }
        if (pageNo != totalPages){
            displayPreviousNext[1] = true;
        } else {
            displayPreviousNext[1] = false;
        }
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("fromTo", fromTo);
        model.addAttribute("size", size);
        model.addAttribute("displayPreviousNext", displayPreviousNext);
    }
}
