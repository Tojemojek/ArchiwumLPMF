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
    public List<Song> processOneFile(@RequestParam("from") Integer fromList,
                                     @RequestParam("toList") Integer toList) {

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

    @RequestMapping(value = "/persist/singleFile/{singleListNo}", method = RequestMethod.GET)
    public void processOneFile(@PathVariable("singleListNo") Integer singleListNo) {
        convertSingleLPMFFile.convertAndPersistSingleLPMFFile(singleListNo);
    }

    @RequestMapping(value = "/download/{noOfLists}", method = RequestMethod.GET)
    public void downloadListsToDrive(@PathVariable("noOfLists") Integer noOfLists) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(noOfLists);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania " + noOfLists + "  list wynosi " + TimeUnit.MILLISECONDS.toSeconds(endDownload - startDownload) + " sekund");
    }

    @RequestMapping(value = "/download/single", method = RequestMethod.GET)
    public void downloadSingleListToDrive(@RequestParam("noOfList") Integer noOfLists) {
        toDonwloadHtml.downloadSingleList(noOfLists);
    }
}