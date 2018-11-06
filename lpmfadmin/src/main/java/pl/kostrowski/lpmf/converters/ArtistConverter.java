package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.dictionaries.AllDictionaries;
import pl.kostrowski.lpmf.dto.SingleLpmfDto;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.ListInfo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ArtistConverter {

    public List<Artist> convert(String singleEntryFromDtos, ListInfo listInfo) {

        Map<String, String> artistDictionary = AllDictionaries.getArtistDictionary();
        List<Artist> allArtists = new LinkedList<>();

        String artistNameFromDto = singleEntryFromDtos.trim();

        if (artistDictionary.containsKey(artistNameFromDto)) {
            artistNameFromDto = artistDictionary.get(artistNameFromDto);
        }

        String[] splitedArtists = artistNameFromDto.split("/");
        String trimmed;

        for (int i = 0; i < splitedArtists.length; i++) {
            trimmed = splitedArtists[i].trim();

            Artist artist = new Artist();
            artist.setFullName(trimmed);
            artist.setFirstTimeInList(listInfo);
            allArtists.add(artist);
        }

        return allArtists;
    }

}
