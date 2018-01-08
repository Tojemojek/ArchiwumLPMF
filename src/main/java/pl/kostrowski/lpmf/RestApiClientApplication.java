package pl.kostrowski.lpmf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.FullListRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.service.ToSaveToDb;

@SpringBootApplication
public class RestApiClientApplication implements CommandLineRunner {

    @Autowired
    private FullListRepository fullListRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SongRepository songRepository;


    public static void main(String[] args) {
        SpringApplication.run(RestApiClientApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        ToSaveToDb toSaveToDb = new ToSaveToDb(fullListRepository, artistRepository, movieRepository, songRepository);

        for (int i = 0; i < 234; i++) {
            toSaveToDb.save(i);
        }
    }
}
