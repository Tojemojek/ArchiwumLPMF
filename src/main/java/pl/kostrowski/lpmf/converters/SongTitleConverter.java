package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.model.ListInfo;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.model.dictionaries.AllDictionaries;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SongTitleConverter {

    public List<Song> convertOld(List<SingleEntryInListDto> singleEntryInListDtos) {

        Map<String, String> songDictionary = AllDictionaries.getSongDictionary();
        List<Song> songs = new LinkedList<>();
        String listNumberAndDate;


        for (SingleEntryInListDto singleEntryInListDto : singleEntryInListDtos) {
            String songTitlefromDto = singleEntryInListDto.getFullSongTitle();

            listNumberAndDate = singleEntryInListDto.getNrAndDateOfList();

            if (songDictionary.containsKey(songTitlefromDto.trim())) {
                songTitlefromDto = songDictionary.get(songTitlefromDto);
            }
            Song song = new Song();
            song.setTitle(songTitlefromDto);
//            song.setFirstTimeInList(listNumberAndDate);
            songs.add(song);
        }

        return songs;
    }

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
