package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.service.ConvertSingleLPMFFile;
import pl.kostrowski.lpmf.service.ToDonwloadHtml;

@Controller
public class UpdateController {

    Logger LOG = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    ToDonwloadHtml toDonwloadHtml;

    @Autowired
    JsoupFileParser jsoupFileParser;

    @Autowired
    ConvertSingleLPMFFile convertSingleLPMFFile;

    @Autowired
    SongRepository songRepository;


    @RequestMapping(value = "/persist/manyFiles", method = RequestMethod.GET)
    public String processManyFiles(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                       @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long start = System.currentTimeMillis();
        for (int i = fromList; i <= toList; i++) {
            long startIn = System.currentTimeMillis();
            convertSingleLPMFFile.convertAndPersistSingleLPMFFile(i);
            long stopIn = System.currentTimeMillis();
            LOG.info("Konwersja pliku nr " + i + " zajęła " + (stopIn - startIn) + " milisekund.");
        }

        long stop = System.currentTimeMillis();
        LOG.info("Konwersja od pliku nr " + fromList + " do pliku numer " + toList + " zajęła " + (stop - start) + " milisekund.");
        return "redirect:/duplicates";
    }

    @RequestMapping(value = "/persist/singleFile", method = RequestMethod.GET)
    public String  processOneFile(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfList) {
        convertSingleLPMFFile.convertAndPersistSingleLPMFFile(noOfList);
        return "redirect:/duplicates";
    }

    @RequestMapping(value = "/duplicates", method = RequestMethod.GET)
    public String findAndMarkDuplicates(){
        convertSingleLPMFFile.markDuplicateSongs();
        return "redirect:/";
    }

    @RequestMapping(value = "/download/many", method = RequestMethod.GET)
    public String downloadListsToDrive(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                     @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(fromList, toList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania " + (toList - fromList) + "  list wynosi " + (endDownload - startDownload) + " milisekund");
        return "redirect:/";
    }

    @RequestMapping(value = "/download/single", method = RequestMethod.GET)
    public String downloadSingleListToDrive(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfLists) {
        toDonwloadHtml.downloadSingleList(noOfLists);
        return "redirect:/";
    }
}