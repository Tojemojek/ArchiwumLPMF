package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kostrowski.lpmf.dto.views.MedalTableArtist;
import pl.kostrowski.lpmf.dto.views.MedalTableMovie;
import pl.kostrowski.lpmf.dto.views.MedalTableSong;
import pl.kostrowski.lpmf.service.MedalTableDisplay;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class MedalController {

    final private MedalTableDisplay medalTableDisplay;

    @Autowired
    public MedalController(MedalTableDisplay medalTableDisplay) {
        this.medalTableDisplay = medalTableDisplay;
    }

    @RequestMapping(value = "/medals/songs")
    public String showSongsMedals(Model model) {
        List<MedalTableSong> allMedalSongs = medalTableDisplay.medalTableSongs();
        model.addAttribute("allMedalSongs", allMedalSongs);
        return "medal/songs";
    }

    @RequestMapping(value = "/medals/artists")
    public String showArtisMedals(Model model) {
        List<MedalTableArtist> allMedalArtists = medalTableDisplay.medalTableArtists();
        model.addAttribute("allMedalArtists", allMedalArtists);
        return "medal/artists";
    }

    @RequestMapping(value = "/medals/movies")
    public String showMoviesMedals(Model model) {
        List<MedalTableMovie> allMedalMovies = medalTableDisplay.medalTableMovies();
        model.addAttribute("allMedalMovies", allMedalMovies);
        return "medal/movies";
    }

}
