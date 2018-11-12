package pl.kostrowski.lpmf.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import pl.kostrowski.lpmf.dto.SingleLpmfDto;
import pl.kostrowski.lpmf.model.RawLpmfData;
import pl.kostrowski.lpmf.repository.RawDataRepository;
import pl.kostrowski.lpmf.service.GzipUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Component
public class JsoupHtmlDataParser {

    private final Logger LOG = LoggerFactory.getLogger(JsoupHtmlDataParser.class);

    private final RawDataRepository rawDataRepository;
    private final GzipUtil gzipUtil;

    @Autowired
    public JsoupHtmlDataParser(RawDataRepository rawDataRepository, GzipUtil gzipUtil) {
        this.rawDataRepository = rawDataRepository;
        this.gzipUtil = gzipUtil;
    }

    public List<List<SingleLpmfDto>> parseBatchFilesToObjects(int fromList, int toList){
        List<List<SingleLpmfDto>> allFilesData = new LinkedList<>();

        for (int i = fromList; i <= toList; i++){
            List<SingleLpmfDto> singleLpmfDtos = parseSingleFileToObjects(i);
            allFilesData.add(singleLpmfDtos);
        }
        return allFilesData;
    }

    @SuppressWarnings("UnusedAssignment")
    private List<SingleLpmfDto> parseSingleFileToObjects(int listId) {

        long start = System.currentTimeMillis();

        Optional<RawLpmfData> byId = rawDataRepository.findById(listId);
        RawLpmfData rawLpmfData;
        if (byId.isPresent()) {
            rawLpmfData = byId.get();
        } else {
            return null;
        }

        LOG.debug("Przetwarzenie listy nr:" + listId);

        Document doc = Jsoup.parse(HtmlUtils.htmlUnescape(gzipUtil.unzip(rawLpmfData.getRawPage())));

        List<SingleLpmfDto> wholeSingleList = new LinkedList<>();

        String nrIDataListy = doc.getElementsByClass("lpmf-not-head").text();

        Elements singleList = doc.getElementsByClass("inner-p");

        String position = "";
        String fullArtist = "";
        String fullSongTitle = "";
        String fullMovieTitle = "";
        String coverLink = "";

        for (Element songDetail : singleList) {
            SingleLpmfDto singleEntryInListDto = new SingleLpmfDto();

            position = songDetail.getElementsByClass("cfn").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullArtist = songDetail.getElementsByClass("artist-name").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullMovieTitle = songDetail.getElementsByClass("song-title").get(0).text().trim().replaceAll("(\\s)+", "$1");
            fullSongTitle = songDetail.getElementsByClass("song-sub-title").get(0).text().trim().replaceAll("(\\s)+", "$1");
            coverLink = songDetail.getElementsByTag("img").get(0).attributes().get("src").trim();

            singleEntryInListDto.setNrIDataListy(nrIDataListy);
            singleEntryInListDto.setPosition(Integer.parseInt(position));
            singleEntryInListDto.setFullArtist(fullArtist);
            singleEntryInListDto.setFullMovieTitle(fullMovieTitle);
            singleEntryInListDto.setFullSongTitle(fullSongTitle);
            singleEntryInListDto.setCoverLink(coverLink);

            wholeSingleList.add(singleEntryInListDto);

            LOG.debug("Pozycja " + position + " artysta " + fullArtist + " film " + fullMovieTitle + " utwór " + fullSongTitle);
        }

        long end = System.currentTimeMillis();

        long totalTime = TimeUnit.MILLISECONDS.toSeconds(end - start);

        LOG.debug("Czas przetwarzania: " + totalTime + "s");

        return wholeSingleList;
    }
}
