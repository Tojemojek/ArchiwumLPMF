package pl.kostrowski.lpmf.service;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import pl.kostrowski.lpmf.dictionaries.PathsToUrls;
import pl.kostrowski.lpmf.model.RawLpmfData;
import pl.kostrowski.lpmf.repository.RawDataRepository;

import java.io.IOException;

@Service
public class ToDonwloadAndPersist {

    private final Logger LOG = LoggerFactory.getLogger(ToDonwloadAndPersist.class);

    private final String URL_ADDRESS = PathsToUrls.REMOTE_URL.getPath();

    private RawDataRepository rawDataRepository;

    @Autowired
    public ToDonwloadAndPersist(RawDataRepository rawDataRepository) {
        this.rawDataRepository = rawDataRepository;
    }

    public void downloadLists(int fromList, int toList) {

        for (int i = fromList; i <= toList; i++) {
            persistToDb(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                LOG.error(e.toString());
            }
        }
    }

    public void downloadSingleList(int noOfList) {
            persistToDb(noOfList);
    }

    private void persistToDb(Integer noOfList) {

        String urlPath = URL_ADDRESS + noOfList;

        String rawHtml = "";
        try {
            rawHtml = Jsoup.connect(urlPath).get().html();
        } catch (IOException e) {
            LOG.error(e.toString());
        }

        RawLpmfData rawLpmfData = new RawLpmfData();
        rawLpmfData.setId(noOfList);
        rawLpmfData.setRawPage(HtmlUtils.htmlEscape(rawHtml));

        rawDataRepository.save(rawLpmfData);

    }
}
