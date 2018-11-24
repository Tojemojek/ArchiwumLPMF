package pl.kostrowski.lpmf.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManualUpdateList {

    private static int NUMBER_OF_LIST = 0;

    @Autowired
    BatchConvertLPMFFile batchConvertLPMFFile;

    @Autowired
    ToDonwloadAndPersist toDonwloadAndPersist;


    @Before
    public void init() {
        NUMBER_OF_LIST = 259;
    }


    @Test
    public void singleBatchConvertAndPersist() {
        toDonwloadAndPersist.downloadSingleList(NUMBER_OF_LIST);
        batchConvertLPMFFile.batchConvertAndPersist(NUMBER_OF_LIST, NUMBER_OF_LIST);
    }
}