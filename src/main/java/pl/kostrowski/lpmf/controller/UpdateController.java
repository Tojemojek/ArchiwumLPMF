package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.service.ConvertSingleLPMFFile;
import pl.kostrowski.lpmf.service.MakeUnique;
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
    MakeUnique makeUnique;

    @Autowired
    SongRepository songRepository;


    @RequestMapping(value = "/persist/manyFiles", method = RequestMethod.GET)
    public List<Song> processOneFile(@RequestParam("from") Integer fromList,
                                     @RequestParam("toList") Integer toList) {
        for (int i = fromList; i <= toList; i++) {
            convertSingleLPMFFile.convertAndPersistSingleLPMFFile(i);
        }
        return (songRepository.findAll());
    }


    @RequestMapping(value = "/persist/singleFile/{singleListNo}", method = RequestMethod.GET)
    public void processOneFile(@PathVariable("singleListNo") Integer singleListNo) {
        convertSingleLPMFFile.convertAndPersistSingleLPMFFile(singleListNo);
    }

    @RequestMapping(value = "/persist/movies/{noOfLists}", method = RequestMethod.GET)
    public void persistUniquleMoviesFromHtmlFiles(@PathVariable("noOfLists") Integer noOfLists) {
        makeUnique.persistUniqueMovies(noOfLists);
    }

    @RequestMapping(value = "/persist/artists/{noOfLists}", method = RequestMethod.GET)
    public void persistUniquleArtistFromHtmlFiles(@PathVariable("noOfLists") Integer noOfLists) {
        makeUnique.persistUniqueArtists(noOfLists);
    }

    @RequestMapping(value = "/persist/songs/{noOfLists}", method = RequestMethod.GET)
    public void persistUniquleSongsFromHtmlFiles(@PathVariable("noOfLists") Integer noOfLists) {
        makeUnique.persistUniqueSongs(noOfLists);
    }

    @RequestMapping(value = "/persist/all/{noOfLists}", method = RequestMethod.GET)
    public void persistUniquleAllFromHtmlFiles(@PathVariable("noOfLists") Integer noOfLists) {
        makeUnique.persistUniqueArtists(noOfLists);
        makeUnique.persistUniqueMovies(noOfLists);
        makeUnique.persistUniqueSongs(noOfLists);
    }

    @RequestMapping(value = "/download/{noOfLists}", method = RequestMethod.GET)
    public void downloadListsToDrive(@PathVariable("noOfLists") Integer noOfLists) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(noOfLists);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania listy numer : " + noOfLists + " wynosi " + TimeUnit.MILLISECONDS.toSeconds(startDownload - endDownload));
    }

    @RequestMapping(value = "/jsoup/{noOfList}", method = RequestMethod.GET)
    public List<SingleEntryInListDto> jsouParse(@PathVariable("noOfList") Integer noOfList) {

        long startDownload = System.currentTimeMillis();

        List<SingleEntryInListDto> singleEntryInListDto = jsoupFileParser.makeDOMfor(noOfList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Parsing time: " + TimeUnit.MILLISECONDS.toSeconds(startDownload - endDownload));

        return singleEntryInListDto;

    }

}