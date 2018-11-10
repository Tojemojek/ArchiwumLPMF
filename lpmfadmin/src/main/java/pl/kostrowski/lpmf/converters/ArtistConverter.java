package pl.kostrowski.lpmf.converters;

import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.ListInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ArtistConverter {

    private Map<String, String> artistDictionary = new HashMap<>();

    public List<Artist> convert(String singleEntryFromDtos, ListInfo listInfo) {

        List<Artist> allArtists = new LinkedList<>();

        String artistNameFromDto = singleEntryFromDtos.trim();

        if (artistDictionary.containsKey(artistNameFromDto)) {
            artistNameFromDto = artistDictionary.get(artistNameFromDto);
        }

        String[] splitedArtists = artistNameFromDto.split("/");
        String trimmed;

        for (int i = 0; i < splitedArtists.length; i++) {
            trimmed = splitedArtists[i].trim();

            if (artistDictionary.containsKey(trimmed)) {
                trimmed = artistDictionary.get(trimmed);
            }

            Artist artist = new Artist();
            artist.setFullName(trimmed);
            artist.setFirstTimeInList(listInfo);
            allArtists.add(artist);
        }

        return allArtists;
    }

    public void setArtistDictionary(Map<String, String> artistDictionary) {
        this.artistDictionary = artistDictionary;
    }
}
