package pl.kostrowski.lpmf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.kostrowski.lpmf.model.Artist;
import pl.kostrowski.lpmf.model.Movie;
import pl.kostrowski.lpmf.model.Song;
import pl.kostrowski.lpmf.repository.SongRepository;
import pl.kostrowski.lpmf.model.SongInList;
import pl.kostrowski.lpmf.repository.ArtistRepository;
import pl.kostrowski.lpmf.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CleanAndParse {

    Logger LOG = LoggerFactory.getLogger(CleanAndParse.class);

    private MovieRepository movieRepository;
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    public CleanAndParse(MovieRepository movieRepository, SongRepository songRepository, ArtistRepository artistRepository) {
        this.movieRepository = movieRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    private final String path = "static/";


    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public String readFromFile(Integer noOfList) {

        Integer nrListy = noOfList;

        String path = this.path;
        String fileName = "lista" + nrListy + ".html";

        StringBuilder sb = new StringBuilder();

        String tresc = new String();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new ClassPathResource(path + fileName).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sCurrentLine;
        try {
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }


    public String getListOnly(String fullHtml) {

        String start = "<div class=\"lpmf-not-head cfn\">";
        String end = "<div class=\"lpmf-right\">";

        int startIndex = fullHtml.indexOf(start);
        int stopIndex = fullHtml.indexOf(end);

        return fullHtml.substring(startIndex, stopIndex);
    }

    public List<String> getListNoAndDate(String listOnly) {

        List<String> dane = new ArrayList<>();

        String noOfListStart = "<b>";
        String noOfListEnd = "</b>";

        String dateOfListStart = "<b class=\"small\">";
        String dateOfListEnd = "</b>";

        int startNoIndex = listOnly.indexOf(noOfListStart);
        int stopNoIndex = listOnly.indexOf(noOfListEnd);

        int startDateIndex = listOnly.indexOf(dateOfListStart);
        int stopDateIndex = listOnly.indexOf(dateOfListEnd, startDateIndex);

        dane.add(listOnly.substring(startNoIndex + 3, stopNoIndex));
        dane.add(listOnly.substring(startDateIndex + 28, stopDateIndex));

        return dane;
    }


    public String[] splitToListPosArray(String listOnly) {

        return listOnly.split("<div class=\"position fl\"><span><b class=\"cfn\">");

    }

    public SongInList getSingleSongInList(String posOnly) throws StringIndexOutOfBoundsException {

        String posEnd = "</b>";

        String coverArtStart = "<img src=\"";
        String coverArtEnd = "\"";

        String artistNameStart = "<div class=\"artist-name";
        String artistNameEnd = "</div>";

        String filmTitleStart = "<div class=\"song-title";
        String filmTitleEnd = "</div>";

        String songTitleStart = "<div class=\"song-sub-title";
        String songTitleEnd = "</div>";

        int startPos = 0;
        int stopPos = posOnly.indexOf(posEnd);

        int startCoverArt = posOnly.indexOf(coverArtStart) + 10;
        int stopCoverArt = posOnly.indexOf(coverArtEnd, startCoverArt);

        int startArtist = posOnly.indexOf(artistNameStart) + 32;
        int stopArtist = posOnly.indexOf(artistNameEnd, startArtist);

        int startFilmTitle = posOnly.indexOf(filmTitleStart) + 31;
        int stopFilmTitle = posOnly.indexOf(filmTitleEnd, startFilmTitle);

        int startSongTitle = posOnly.indexOf(songTitleStart) + 35;
        int stopSongTitle = posOnly.indexOf(songTitleEnd, startSongTitle);

        String pos = posOnly.substring(startPos, stopPos);
        String artists = posOnly.substring(startArtist, stopArtist).trim();
        String movieTitle = posOnly.substring(startFilmTitle, stopFilmTitle).trim();
        String songCoverArtLink = posOnly.substring(startCoverArt, stopCoverArt).trim();
        String songTitle = posOnly.substring(startSongTitle, stopSongTitle).trim();

        List<Artist> artistsList = makeArtists(artists);

        Movie movie = new Movie();
        Movie movieFromRepository;

        try {
            movieFromRepository = movieRepository.findMovieByTitle(movieTitle);
        } catch (Exception e) {
            movieFromRepository = null;
        }

        if (movieFromRepository != null) {
            movie.setId(movieFromRepository.getId());
            movie.setTitle(movieTitle);
        } else{
            movie.setTitle(movieTitle);
            movieRepository.save(movie);
        }


        Song song = new Song();
        song.setAuthors(artistsList);
        song.setMovie(movie);
        song.setCoverLink(songCoverArtLink);
        song.setTitle(songTitle);

        SongInList songInList = new SongInList();

        songInList.setPos(Integer.valueOf(pos));
        songInList.setSong(song);

        return songInList;
    }


    private List<Artist> makeArtists(String artists) {

        String[] split = artists.split("/");

        List<Artist> artistsList = new ArrayList<>();

        for (String s : split) {
            Artist artist = new Artist();

            String[] split1 = s.split(" ");

            if (split1.length == 1) {
                artist.setName("");
                artist.setSurname(split1[0]);
            } else {
                for (int i = 0; i < split1.length - 1; i++) {
                    if (artist.getName() != null) {
                        artist.setName((artist.getName() + " " + split1[i]).trim());
                    } else {
                        artist.setName((split1[i]).trim());
                    }
                }
                artist.setSurname((split1[split1.length - 1]).trim());
            }


            Artist artistFromRepository;

            try {
                artistFromRepository = artistRepository.findArtistByNameAndSurname(artist.getName(), artist.getSurname());
            } catch (Exception e) {
                artistFromRepository = null;
            }

            if (artistFromRepository != null) {
                artist.setId(artistFromRepository.getId());
                artistsList.add(artist);

            } else {
                artistRepository.save(artist);
                artistsList.add(artist);
            }
        }
        return artistsList;
    }

    private LocalDate parseDateToLocalDate(String dateInString) {
        return null;
    }
}
