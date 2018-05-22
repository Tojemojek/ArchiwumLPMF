package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    LPMFPositionRepository lpmfPositionRepository;
    @Autowired
    SongRepository songRepository;


    @RequestMapping(value = "/")
    public String displayMenu() {
        return "index";
    }

    @RequestMapping(value = "/download")
    public String showDonwloadPage() {
        return "download";
    }

    @RequestMapping(value = "/persist")
    public String showPersistPage() {
        return "persist";
    }

    @RequestMapping(value = "/lists")
    public String showSingleList(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                 @RequestParam(value = "toList", defaultValue = "1") Integer toList,
                                 Model model) {
        List<LPMFPosition> allByNoOfListOOrderByPos = lpmfPositionRepository.findByNoOfList(fromList);
        model.addAttribute("lists", allByNoOfListOOrderByPos);
        return "singleList";
    }

    @RequestMapping(value = "/songPos")
    public String showMe(@RequestParam(value = "songId", defaultValue = "32") Integer songId, Model model) {
        List<LPMFPosition> findOne = lpmfPositionRepository.customFindBySong(Long.valueOf(songId));
        model.addAttribute("singleSong",findOne);
        return "songPos";
    }

    @RequestMapping(value = "/allSongs")
    public String showAllSongs(@RequestParam(value = "songId", defaultValue = "32") Integer songId, Model model) {
        List<Song> allSongs = songRepository.findAll();
        model.addAttribute("allSongs", allSongs);
        return "allSongs";
    }
}
