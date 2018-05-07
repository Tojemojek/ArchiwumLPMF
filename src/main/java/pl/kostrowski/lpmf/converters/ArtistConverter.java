package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dto.SingleEntryInListDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.dictionaries.AllDictionaries;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ArtistConverter {

    public List<Artist> convert(List<SingleEntryInListDto> singleEntryInListDtos) {

        Map<String, String> artistDictionary = AllDictionaries.getArtistDictionary();
        List<Artist> allArtists = new LinkedList<>();
        String listNumberAndDate;


        for (SingleEntryInListDto singleEntryInListDto : singleEntryInListDtos) {
            String artistNameFromDto = singleEntryInListDto.getFullArtist();

            listNumberAndDate = singleEntryInListDto.getNrAndDateOfList();

            if (artistDictionary.containsKey(artistNameFromDto.trim())) {
                artistNameFromDto = artistDictionary.get(artistNameFromDto);
            }

           String[] splitedArtists = artistNameFromDto.split("/");
            String trimmed;

            for (int i = 0; i < splitedArtists.length; i++) {
                trimmed = splitedArtists[i].trim();

                Artist artist = new Artist();
                artist.setFullName(trimmed);
                artist.setFirstTimeInList(listNumberAndDate);
                allArtists.add(artist);
            }
        }
        return allArtists;
    }
}
