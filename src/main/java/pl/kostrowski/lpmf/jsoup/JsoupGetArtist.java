package pl.kostrowski.lpmf.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.service.Path;

import java.io.File;
import java.io.IOException;

@Component
public class JsoupGetArtist {

    private final String path = Path.PATH_FOR_LOCAL_HTML.getPath();


    public void makeDOMfor(int i){

        File processed = new File(path + "lista" + i + ".html");

        Document doc = null;
        try {
            doc = Jsoup.parse(processed, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements songDetails = doc.getElementsByClass("song-details");

        String fullArtist = "";
        String fullSongTitle = "";
        String fullMovieTitle = "";

        for (Element songDetail : songDetails) {
            fullArtist = songDetail.getElementsByClass("artist-name").get(0).text();
            fullMovieTitle = songDetail.getElementsByClass("song-title").get(0).text();
            fullSongTitle = songDetail.getElementsByClass("song-sub-title").get(0).text();
            System.out.println(fullArtist + " " + fullMovieTitle + " " + fullSongTitle);
        }

        System.out.println("skończyło");
    }


}
