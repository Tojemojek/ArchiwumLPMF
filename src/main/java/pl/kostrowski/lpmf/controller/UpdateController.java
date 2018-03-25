package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kostrowski.lpmf.dto.SingleSongInListDto;
import pl.kostrowski.lpmf.jsoup.JsoupFileParser;
import pl.kostrowski.lpmf.service.ToDonwloadHtml;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import pl.kostrowski.lpmf.service.ToSaveToDb;

@RestController
public class UpdateController {

    Logger LOG = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    ToDonwloadHtml toDonwloadHtml;

    @Autowired
    JsoupFileParser jsoupFileParser;

    @RequestMapping(value = "/download/{noOfLists}", method = RequestMethod.GET)
    public void downloadListsToDrive(@PathVariable("noOfLists") Integer noOfLists) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(noOfLists);

        long endDownload = System.currentTimeMillis();

        LOG.info("Czas pobrania listy numer : " + noOfLists + " wynosi " + TimeUnit.MILLISECONDS.toSeconds(startDownload - endDownload));
    }

    @RequestMapping(value = "/jsoup/{noOfList}", method = RequestMethod.GET)
    public List<SingleSongInListDto> jsouParse(@PathVariable("noOfList") Integer noOfList) {

        long startDownload = System.currentTimeMillis();

        List<SingleSongInListDto> singleSongInListDto = jsoupFileParser.makeDOMfor(noOfList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Parsing time: " + TimeUnit.MILLISECONDS.toSeconds(startDownload - endDownload));

        return singleSongInListDto;

    }

}