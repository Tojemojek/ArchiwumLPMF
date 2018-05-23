package pl.kostrowski.lpmf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kostrowski.lpmf.dto.MedalTableSongs;
import pl.kostrowski.lpmf.service.DisplayService;

import java.util.List;

@RestController
public class ForTestController {

    @Autowired
    DisplayService displayService;

    @RequestMapping(value = "/test")
    public List<MedalTableSongs> dupa(Model model) {
        List<MedalTableSongs> allMedal = displayService.medalTable();
        return allMedal;
    }
}
