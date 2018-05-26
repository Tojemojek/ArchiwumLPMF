package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;
import pl.kostrowski.lpmf.service.DisplayService;

import java.util.List;

@Controller
public class AllController {

    @Autowired
    LPMFPositionRepository lpmfPositionRepository;

    @Autowired
    DisplayService displayService;

    @RequestMapping(value = "/")
    public String displayMenu() {
        return "index";
    }

    @RequestMapping(value = "/single/lists")
    public String showSingleList(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                 @RequestParam(value = "toList", defaultValue = "1") Integer toList,
                                 Model model) {
        List<LPMFPosition> allByNoOfListOOrderByPos = lpmfPositionRepository.findByNoOfList(fromList);
        model.addAttribute("lists", allByNoOfListOOrderByPos);
        return "/singleList";
    }

    @RequestMapping(value = "/single/songPos")
    public String showMe(@RequestParam(value = "songId", defaultValue = "32") Integer songId, Model model) {
        List<LPMFPosition> findOne = lpmfPositionRepository.customFindBySong(Long.valueOf(songId));
        model.addAttribute("singleSong",findOne);
        return "/singleSongPos";
    }

    @RequestMapping(value = "/allSongs")
    public String showAllSongs(Model model) {
        List<Song> allSongs = displayService.showAllSongs();
        model.addAttribute("allSongs", allSongs);
        return "/all/songs";
    }

    @RequestMapping(value = "/allMovies")
    public String showAllMovies(Model model) {
        List<Movie> allMovies = displayService.showAllMovies();
        model.addAttribute("allMovies", allMovies);
        return "/all/movies";
    }

    @RequestMapping(value = "/allArtists")
    public String showAllArtists(Model model) {
        List<Artist> allArtists = displayService.showAllArtists();
        model.addAttribute("allArtists", allArtists);
        return "/all/artists";
    }


}
