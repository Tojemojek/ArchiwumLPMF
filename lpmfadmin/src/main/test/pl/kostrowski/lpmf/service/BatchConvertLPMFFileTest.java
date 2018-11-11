package pl.kostrowski.lpmf.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.LPMFPositionRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;
import pl.kostrowski.lpmf.repository.SongRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchConvertLPMFFileTest {

    @Autowired
    BatchConvertLPMFFile batchConvertLPMFFile;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    LPMFPositionRepository lpmfPositionRepository;

    @Test
    public void batchConvertAndPersist() {

        batchConvertLPMFFile.batchConvertAndPersist(0,256);

        Assert.assertEquals("Liczba artystów", 363, artistRepository.count());
        Assert.assertEquals("Liczba utworów", 547, songRepository.count());
        Assert.assertEquals("Liczba filmów", 431, movieRepository.count());
        Assert.assertEquals("Liczba Pozycji", 257*20, lpmfPositionRepository.count());

    }

    @Test
    public void multipleBatchConvertAndPersist() {

        batchConvertLPMFFile.batchConvertAndPersist(0,15);
        batchConvertLPMFFile.batchConvertAndPersist(13,40);
        batchConvertLPMFFile.batchConvertAndPersist(30,200);
        batchConvertLPMFFile.batchConvertAndPersist(180,256);

        batchConvertLPMFFile.batchConvertAndPersist(58,256);

        Assert.assertEquals("Liczba artystów", 363, artistRepository.count());
        Assert.assertEquals("Liczba utworów", 547, songRepository.count());
        Assert.assertEquals("Liczba filmów", 431, movieRepository.count());
        Assert.assertEquals("Liczba Pozycji", 257*20, lpmfPositionRepository.count());

    }

    @Test
    public void singleBatchConvertAndPersist() {

        batchConvertLPMFFile.batchConvertAndPersist(257,257);

    }

    @Test
    public void convertAll() {
        batchConvertLPMFFile.batchConvertAndPersist(0,257);
    }


}