package OLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.SongRepository;

import java.util.List;

@Component
public class ParseSong {

    private SongRepository songRepository;

    @Autowired
    public ParseSong(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song makeSong(List<Artist> artistsList,
                         Movie movie,
                         String songCoverArtLink,
                         String songTitle) {

        Song song = new Song();
        song.setAuthors(artistsList);
        song.setMovie(movie);
        song.setCoverLink(songCoverArtLink);
        song.setTitle(songTitle);


        Song songFromRepository;

        try {
            songFromRepository = songRepository.findSongByTitleAndMovie(songTitle, movie);
        } catch (Exception e) {
            songFromRepository = null;
        }

        if (songFromRepository != null) {
            song.setId(songFromRepository.getId());
            songRepository.save(song);
        } else {
            songRepository.save(song);
        }

        return song;
    }

}
