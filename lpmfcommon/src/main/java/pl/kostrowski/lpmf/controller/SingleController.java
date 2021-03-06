package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.dto.ArtistSongsDto;
import pl.kostrowski.lpmf.dto.MovieSongsDto;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.service.DisplayAllService;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class SingleController {

    private final DisplayAllService displayAllService;

    @Autowired
    public SingleController(DisplayAllService displayAllService) {
        this.displayAllService = displayAllService;
    }

    @RequestMapping(value = "/single/list")
    public String showSingleList(@RequestParam(value = "listNo", defaultValue = "1") Integer listNo,
                                 Model model) {
        List<LPMFPosition> allByNoOfListOOrderByPos = displayAllService.findByNoOfList(listNo);
        model.addAttribute("lists", allByNoOfListOOrderByPos);
        return "single/list";
    }

    @RequestMapping(value = "/single/song")
    public String showMeSong(@RequestParam(value = "songId", defaultValue = "32") Integer songId,
                             Model model) {
        List<LPMFPosition> findSongs = displayAllService.customFindBySongId(Long.valueOf(songId), model);
        model.addAttribute("singleSong", findSongs);
        return "single/song";
    }

    @RequestMapping(value = "/single/artist")
    public String showMeArtist(@RequestParam(value = "artistId", defaultValue = "32") Integer artistId,
                               Model model) {
        ArtistSongsDto singleArtist = displayAllService.customFindArtistById(Long.valueOf(artistId), model);
        model.addAttribute("singleArtist", singleArtist);
        return "single/artist";
    }

    @RequestMapping(value = "/single/movie")
    public String showMeMovie(@RequestParam(value = "movieId", defaultValue = "32") Integer movieId,
                              Model model) {
        MovieSongsDto singleMovie = displayAllService.customFindMovieById(Long.valueOf(movieId),model);
        model.addAttribute("singleMovie", singleMovie);
        return "single/movie";
    }
}
