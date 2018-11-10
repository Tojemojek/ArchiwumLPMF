package pl.kostrowski.lpmf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SameReturnValue")
@Controller
public class TechController {

    @RequestMapping(value = "/download")
    public String showDonwloadPage() {
        return "/tech/download";
    }

    @RequestMapping(value = "/persist")
    public String showPersistPage() {
        return "/tech/persist";
    }

}
