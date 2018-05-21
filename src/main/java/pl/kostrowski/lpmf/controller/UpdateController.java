package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.service.ConvertSingleLPMFFile;
import pl.kostrowski.lpmf.service.ToDonwloadHtml;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
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
    public List<Song> processManyFiles(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                       @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long start = System.currentTimeMillis();

        for (int i = fromList; i <= toList; i++) {
            long startIn = System.currentTimeMillis();
            convertSingleLPMFFile.convertAndPersistSingleLPMFFile(i);
            long stopIn = System.currentTimeMillis();
            LOG.info("Konwersja pliku nr " + i + " zajęła " +TimeUnit.MILLISECONDS.toMicros(stopIn - startIn) + " s." );
        }

        long stop = System.currentTimeMillis();

        LOG.info("Konwersja od pliku nr " + fromList + " do pliku numer " + toList + " zajęła " +(stop - start) + " milisekund." );

        return (songRepository.findAll());
    }

    @RequestMapping(value = "/persist/singleFile", method = RequestMethod.GET)
    public void processOneFile(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfList) {
        convertSingleLPMFFile.convertAndPersistSingleLPMFFile(noOfList);
    }

    @RequestMapping(value = "/download/many", method = RequestMethod.GET)
    public void downloadListsToDrive(@RequestParam(value = "fromList", defaultValue = "1") Integer fromList,
                                     @RequestParam(value = "toList", defaultValue = "1") Integer toList) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(fromList, toList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania " + (toList - fromList) + "  list wynosi " + TimeUnit.MILLISECONDS.toSeconds(endDownload - startDownload) + " sekund");
    }

    @RequestMapping(value = "/download/single", method = RequestMethod.GET)
    public void downloadSingleListToDrive(@RequestParam(value = "noOfList", defaultValue = "1") Integer noOfLists) {
        toDonwloadHtml.downloadSingleList(noOfLists);
    }
}