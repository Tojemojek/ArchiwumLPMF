package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.kostrowski.lpmf.RestApiClientApplication;

import java.io.FileWriter;
import java.io.IOException;

public class ToDonwloadHtml {

    private final Logger LOG = LoggerFactory.getLogger(RestApiClientApplication.class);

    private final String URL_ADDRESS = "http://www.rmfclassic.pl/lista-przebojow/notowanie/";
    private String pathToSaveFiles = "c:/temp/listarmf/";


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public void run() {

        for (int i = 0; i <= 233; i++) {
            writeToFile(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
