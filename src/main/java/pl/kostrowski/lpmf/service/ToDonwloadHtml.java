package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kostrowski.lpmf.HistorOfLPMFApp;
import pl.kostrowski.lpmf.dictionaries.PathsToUrls;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class ToDonwloadHtml {

    private final Logger LOG = LoggerFactory.getLogger(HistorOfLPMFApp.class);

    private final String URL_ADDRESS = PathsToUrls.REMOTE_URL.getPath();
    private String pathToSaveFiles = PathsToUrls.LOCAL_COPY_OF_HTML.getPath();

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public void downloadLists(int fromList, int toList) {

        for (int i = fromList; i <= toList; i++) {
            writeToFile(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadSingleList(int noOfList) {
            writeToFile(noOfList);
    }

    private void writeToFile(Integer noOfList) {

        Integer nrListy = noOfList;

        String forObject = restTemplate.getForObject(URL_ADDRESS + nrListy, String.class);

        String fileName = "lista" + noOfList + ".html";

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(pathToSaveFiles + fileName);
            fileWriter.write(forObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
