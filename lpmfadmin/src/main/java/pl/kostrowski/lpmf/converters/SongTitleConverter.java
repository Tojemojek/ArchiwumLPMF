package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Song;

import java.util.HashMap;
import java.util.Map;

@Service
public class SongTitleConverter {

    private Map<String, String> songDictionary = new HashMap<>();

    public Song convert(String singleEntryInListDtos, ListInfo listInfo) {

        String songTitlefromDto = singleEntryInListDtos.trim();

        if (songDictionary.containsKey(songTitlefromDto)) {
            songTitlefromDto = songDictionary.get(songTitlefromDto);
        }

        Song song = new Song();
        song.setTitle(songTitlefromDto);
        song.setFirstTimeInList(listInfo);

        return song;
    }

    public void setSongDictionary(Map<String, String> songDictionary) {
        this.songDictionary = songDictionary;
    }
}
