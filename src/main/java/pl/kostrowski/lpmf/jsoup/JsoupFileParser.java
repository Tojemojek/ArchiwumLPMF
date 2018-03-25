package pl.kostrowski.lpmf.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.dto.SingleSongInListDto;
import pl.kostrowski.lpmf.service.Path;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class JsoupFileParser {

    private final String path = Path.PATH_FOR_LOCAL_HTML.getPath();
    private final Logger LOG = LoggerFactory.getLogger(JsoupFileParser.class);

    public List<SingleSongInListDto> makeDOMfor(int i) {

        long start = System.currentTimeMillis();
        File processed = new File(path + "lista" + i + ".html");

        LOG.info("Przetwarzenie pliku:" + processed.toString());

        Document doc = null;
        try {
            doc = Jsoup.parse(processed, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<SingleSongInListDto> wholeSingleList = new LinkedList<>();


        String nrIDataListy = doc.getElementsByClass("lpmf-not-head").text();


        Elements singleList = doc.getElementsByClass("inner-p");

        String position = "";
        String fullArtist = "";
        String fullSongTitle = "";
        String fullMovieTitle = "";
        String coverLink = "";

        for (Element songDetail : singleList) {
            SingleSongInListDto singleSongInListDto = new SingleSongInListDto();

            position = songDetail.getElementsByClass("cfn").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullArtist = songDetail.getElementsByClass("artist-name").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullMovieTitle = songDetail.getElementsByClass("song-title").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullSongTitle = songDetail.getElementsByClass("song-sub-title").get(0).text().trim().replaceAll("(\\s)+", "$1");
            coverLink = songDetail.getElementsByTag("img").get(0).attributes().get("src").trim();

            singleSongInListDto.setNrIDataListy(nrIDataListy);
            singleSongInListDto.setPosition(position);
            singleSongInListDto.setFullArtist(fullArtist);
            singleSongInListDto.setFullMovieTitle(fullMovieTitle);
            singleSongInListDto.setFullSongTitle(fullSongTitle);
            singleSongInListDto.setCoverLink(coverLink);

            wholeSingleList.add(singleSongInListDto);

            LOG.info("Pozycja " + position + " artysta " + fullArtist + " film " + fullMovieTitle + " utwór " + fullSongTitle);
        }

        long end = System.currentTimeMillis();

        long totalTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        LOG.info("Czas przetwarzania: " + totalTime + "s");

        return wholeSingleList;
    }
}