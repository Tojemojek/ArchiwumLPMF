//package pl.kostrowski.lpmf.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import pl.kostrowski.lpmf.model.FullList;
//import pl.kostrowski.lpmf.model.LPMFPosition;
//import pl.kostrowski.lpmf.repository.ListInfo;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@Component
//public class ToSaveToDb {
//
//    Logger LOG = LoggerFactory.getLogger(ToSaveToDb.class);
//
//
//    private ListInfo fullListRepository;
//
//    @Autowired
//    public ToSaveToDb(ListInfo fullListRepository) {
//        this.fullListRepository = fullListRepository;
//    }
//
//    @Autowired
//    CleanAndParse cleanAndParse;
//
//    public void save(Integer fileNo) {
//
//        String fullHtml = cleanAndParse.readFromFile(fileNo);
//        String listOnly = cleanAndParse.getListOnly(fullHtml);
//
//        List<String> listNoAndDate = cleanAndParse.getListNoAndDate(listOnly);
//
//        String[] stringArray = cleanAndParse.splitToListPosArray(listOnly);
//
//        FullList fullList = new FullList();
//
//        fullList.setNoOfList(Integer.valueOf(listNoAndDate.get(0)));
//        fullList.setDate(listNoAndDate.get(1));
//
//        List<LPMFPosition> lsin = new LinkedList<>();
//
//        try {
//            for (int i = 1; i < stringArray.length; i++) {
//                lsin.add(cleanAndParse.getSingleSongInList(stringArray[i]));
//            }
//        } catch (StringIndexOutOfBoundsException e) {
//            System.out.println("Padło na nr " + fileNo);
//            return;
//        }
//
//        fullList.setSongs(lsin);
//
//        fullListRepository.save(fullList);
//
//        LOG.info("Zapisano w bazie danych plik " + fileNo);
//
//    }
//}
