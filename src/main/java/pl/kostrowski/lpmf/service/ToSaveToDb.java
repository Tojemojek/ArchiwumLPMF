package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.model.FullList;
import pl.kostrowski.lpmf.model.SongInList;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.FullListRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.model.*;
import pl.kostrowski.lpmf.repository.*;

import java.util.LinkedList;
import java.util.List;

@Component
public class ToSaveToDb {
    Logger LOG = LoggerFactory.getLogger(ToSaveToDb.class);

    private FullListRepository fullListRepository;
    private ArtistRepository artistRepository;
    private MovieRepository movieRepository;
    private SongRepository songRepository;

    public ToSaveToDb(FullListRepository fullListRepository, ArtistRepository artistRepository, MovieRepository movieRepository, SongRepository songRepository) {
        this.fullListRepository = fullListRepository;
        this.artistRepository = artistRepository;
        this.movieRepository = movieRepository;
        this.songRepository = songRepository;
    }


    public void save(Integer fileNo) {


        CleanAndParse cap = new CleanAndParse(movieRepository, songRepository, artistRepository);

        String fullHtml = cap.readFromFile(fileNo);
        String listOnly = cap.getListOnly(fullHtml);

        List<String> listNoAndDate = cap.getListNoAndDate(listOnly);

        String[] stringArray = cap.splitToListPosArray(listOnly);

        FullList fullList = new FullList();

        fullList.setNoOfList(Integer.valueOf(listNoAndDate.get(0)));
        fullList.setDate(listNoAndDate.get(1));

        List<SongInList> lsin = new LinkedList<>();

        try {
        for (int i = 1; i < stringArray.length; i++) {
            lsin.add(cap.getSingleSongInList(stringArray[i]));
        }
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Padło na nr " + fileNo);
            return;
        }

        fullList.setSongs(lsin);

        fullListRepository.save(fullList);

        LOG.info("Zapisano w bazie danych plik " + fileNo);

    }
}
