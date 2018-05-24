package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kostrowski.lpmf.dto.MedalTableArtist;
import pl.kostrowski.lpmf.dto.MedalTableMovie;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.service.DisplayService;

import java.util.List;

@RestController
public class ForTestController {

    @Autowired
    DisplayService displayService;

    @RequestMapping(value = "/test")
    public List<MedalTableSongs> test(Model model) {
        List<MedalTableSongs> allMedalSongs = displayService.medalTableSongs();
        return allMedalSongs;
    }

    @RequestMapping(value = "/test2")
    public List<MedalTableArtist> test2(Model model) {
        List<MedalTableArtist> allMedalArtist = displayService.medalTableArtists();
        return allMedalArtist;
    }

    @RequestMapping(value = "/test3")
    public List<MedalTableMovie> test3(Model model) {
        List<MedalTableMovie> allMedalArtist = displayService.medalTableMovies();
        return allMedalArtist;
    }

}
