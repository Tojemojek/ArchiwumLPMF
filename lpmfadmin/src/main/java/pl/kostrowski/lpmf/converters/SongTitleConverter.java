package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dictionaries.AllDictionaries;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Song;

import java.util.Map;

@Service
public class SongTitleConverter {

    public Song convert(String singleEntryInListDtos, ListInfo listInfo) {

        Map<String, String> songDictionary = AllDictionaries.getSongDictionary();

        String songTitlefromDto = singleEntryInListDtos.trim();

        if (songDictionary.containsKey(songTitlefromDto)) {
            songTitlefromDto = songDictionary.get(songTitlefromDto);
        }

        Song song = new Song();
        song.setTitle(songTitlefromDto);
        song.setFirstTimeInList(listInfo);

        return song;
    }

}
