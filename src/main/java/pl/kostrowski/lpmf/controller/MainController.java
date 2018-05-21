package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.model.LPMFPosition;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    LPMFPositionRepository lpmfPositionRepository;


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
        model.addAttribute("lists",allByNoOfListOOrderByPos);
        return "singleList";
    }

}
