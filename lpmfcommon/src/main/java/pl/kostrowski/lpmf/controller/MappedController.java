package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kostrowski.lpmf.model.PreProcesingCleanUpData;
import pl.kostrowski.lpmf.repository.PreProcesingCleanUpRepository;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class MappedController {

    private final PreProcesingCleanUpRepository preProcesingCleanUpRepository;

    @Autowired
    public MappedController(PreProcesingCleanUpRepository preProcesingCleanUpRepository) {
        this.preProcesingCleanUpRepository = preProcesingCleanUpRepository;
    }

    @RequestMapping(value = "/mapped/songs")
    public String showSongsMedals(Model model) {

        List<PreProcesingCleanUpData> mappings = preProcesingCleanUpRepository.findCleanUpDataByCategory("song");

        model.addAttribute("title", "mapped.page.title.songs");
        model.addAttribute("mappings", mappings);

        return "mapped/any";
    }

    @RequestMapping(value = "/mapped/artists")
    public String showArtisMedals(Model model) {

        List<PreProcesingCleanUpData> mappings = preProcesingCleanUpRepository.findCleanUpDataByCategory("artist");

        model.addAttribute("title", "mapped.page.title.artists");
        model.addAttribute("mappings", mappings);

        return "mapped/any";
    }

    @RequestMapping(value = "/mapped/movies")
    public String showMoviesMedals(Model model) {

        List<PreProcesingCleanUpData> mappings = preProcesingCleanUpRepository.findCleanUpDataByCategory("movie");

        model.addAttribute("title", "mapped.page.title.movies");
        model.addAttribute("mappings", mappings);

        return "mapped/any";
    }

}
