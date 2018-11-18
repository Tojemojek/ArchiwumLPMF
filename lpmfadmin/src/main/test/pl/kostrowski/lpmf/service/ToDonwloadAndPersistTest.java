package pl.kostrowski.lpmf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDonwloadAndPersistTest {

    @Autowired
    ToDonwloadAndPersist toDonwloadAndPersist;

    @Test
    public void downloadLists() {
        toDonwloadAndPersist.downloadLists(0,257);

    }

    @Test
    public void downloadSingleList() {
        toDonwloadAndPersist.downloadSingleList(258);
    }
}