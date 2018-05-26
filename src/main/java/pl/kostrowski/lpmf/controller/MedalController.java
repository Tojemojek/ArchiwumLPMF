package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kostrowski.lpmf.dto.MedalTableArtist;
import pl.kostrowski.lpmf.dto.MedalTableMovie;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.service.DisplayService;

import java.util.List;

@Controller
public class MedalController {

    @Autowired
    DisplayService displayService;

    @RequestMapping(value = "/medals/songs")
    public String test(Model model) {
        List<MedalTableSongs> allMedalSongs = displayService.medalTableSongs();
        model.addAttribute("allMedalSongs", allMedalSongs);
        return "/medal/songs";
    }

    @RequestMapping(value = "/medals/artists")
    public String test2(Model model) {
        List<MedalTableArtist> allMedalArtists = displayService.medalTableArtists();
        model.addAttribute("allMedalArtists", allMedalArtists);
        return "/medal/artists";
    }

    @RequestMapping(value = "/medals/movies")
    public String test3(Model model) {
        List<MedalTableMovie> allMedalMovies = displayService.medalTableMovies();
        model.addAttribute("allMedalMovies", allMedalMovies);
        return "/medal/movies";
    }

}
