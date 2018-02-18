package pl.kostrowski.lpmf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.kostrowski.lpmf.jsoup.JsoupGetArtist;
import pl.kostrowski.lpmf.service.ToDonwloadHtml;
//import pl.kostrowski.lpmf.service.ToSaveToDb;
import java.util.concurrent.TimeUnit;

@Controller
public class UpdateController {

    Logger LOG = LoggerFactory.getLogger(UpdateController.class);
//
//    @Autowired
//    ToSaveToDb toSaveToDb;

    @Autowired
    ToDonwloadHtml toDonwloadHtml;

    @Autowired
    JsoupGetArtist jsoupGetArtist;

//    @RequestMapping(value = "/makeFullUpdate/{noOfLists}", method = RequestMethod.GET)
//    public void makeFullUpdate(@PathVariable("noOfLists") Integer noOfLists) {
//        for (int i = 0; i <= noOfLists; i++) {
////            toSaveToDb.save(i);
//        }
//    }

    @RequestMapping(value = "/download/{noOfLists}", method = RequestMethod.GET)
    public void downloadListsToDrive(@PathVariable("noOfLists") Integer noOfLists) {

        long startDownload = System.currentTimeMillis();

        toDonwloadHtml.downloadLists(noOfLists);

        long endDownload = System.currentTimeMillis();

        LOG.info("Download time: "+TimeUnit.MILLISECONDS.toMinutes(startDownload- endDownload));

    }


    @RequestMapping(value = "/jsoup/{noOfList}", method = RequestMethod.GET)
    public void jsouParse(@PathVariable("noOfList") Integer noOfList) {

        long startDownload = System.currentTimeMillis();

        jsoupGetArtist.makeDOMfor(noOfList);

        long endDownload = System.currentTimeMillis();

        LOG.info("Parsing time: "+TimeUnit.MILLISECONDS.toMinutes(startDownload- endDownload));

    }

}