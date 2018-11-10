package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kostrowski.lpmf.service.BatchConvertLPMFFile;
import pl.kostrowski.lpmf.service.ToDonwloadAndPersist;

@SuppressWarnings("SameReturnValue")
@Controller
public class UpdateController {

    private final ToDonwloadAndPersist toDonwloadAndPersist;
    private final BatchConvertLPMFFile batchConvertLPMFFile;
    private final Logger LOG = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    public UpdateController(ToDonwloadAndPersist toDonwloadAndPersist, BatchConvertLPMFFile batchConvertLPMFFile) {
        this.toDonwloadAndPersist = toDonwloadAndPersist;
        this.batchConvertLPMFFile = batchConvertLPMFFile;
    }


    @RequestMapping(value = "/persist/manyFiles", method = RequestMethod.GET)
    public String processManyFiles(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                   @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long start = System.currentTimeMillis();
        batchConvertLPMFFile.batchConvertAndPersist(fromList, toList);
        long stop = System.currentTimeMillis();
        LOG.info("Konwersja od pliku nr " + fromList + " do pliku numer " + toList + " zajęła " + (stop - start) + " milisekund.");
        return "redirect:/duplicates";
    }

    @RequestMapping(value = "/persist/singleFile", method = RequestMethod.GET)
    public String processOneFile(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfList) {
        batchConvertLPMFFile.batchConvertAndPersist(noOfList, noOfList);
        return "redirect:/duplicates";
    }

    @RequestMapping(value = "/duplicates", method = RequestMethod.GET)
    public String findAndMarkDuplicates() {
        batchConvertLPMFFile.markDuplicateSongs();
        return "redirect:/";
    }

    @RequestMapping(value = "/download/many", method = RequestMethod.GET)
    public String downloadListsToDrive(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                       @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long startDownload = System.currentTimeMillis();

        toDonwloadAndPersist.downloadLists(fromList, toList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania " + (toList - fromList) + "  list wynosi " + (endDownload - startDownload) + " milisekund");
        return "redirect:/";
    }

    @RequestMapping(value = "/download/single", method = RequestMethod.GET)
    public String downloadSingleListToDrive(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfLists) {
        toDonwloadAndPersist.downloadSingleList(noOfLists);
        return "redirect:/";
    }
}