package pl.kostrowski.lpmf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiemaController {

    @RequestMapping("/hello")
    public String siema(Model model){
        model.addAttribute("message","Siemanko ze springMVC z użyciem Thymeleaf!");
        return "hello";
    }

    @RequestMapping("/dodaj")
    public String suma(Model model){
        model.addAttribute("message","Chcesz dodać 2 + 2? Przecież wiadomo że to 4");
        return "suma";
    }


}
