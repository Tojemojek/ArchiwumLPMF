package pl.kostrowski.lpmf.repository;

import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.MedalTableArtist;
import pl.kostrowski.lpmf.dto.MedalTableMovie;
import pl.kostrowski.lpmf.dto.MedalTableSongs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class MedalTablesRepository {

    @PersistenceContext
    private EntityManager em;

    private String makeSpecialQuery(String insidePart){

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");

        for (int i = 1; i <=20; i++){
            sb.append("sum(case when sig.pos = ");
            sb.append(i);
            sb.append(" then 1 else 0 end) as poz");
            sb.append(i);
            sb.append(", ");
        }
        sb.append("count(sig.pos) as totalInList, ");
        sb.append(insidePart);
        sb.append(" ORDER BY ");
        for (int i = 1; i <=20; i++){
            sb.append("poz");
            sb.append(i);
            sb.append(" desc, ");
        }
        sb.reverse().deleteCharAt(1).reverse();

        return sb.toString();
    }


    public List<MedalTableSongs> createMedalTableSongsFromDb() {

        StringBuilder sb = new StringBuilder();
        sb.append("s.title as songTitle, m.title as movieTitle ");
        sb.append("FROM songs_in_list sig ");
        sb.append("LEFT JOIN song s ON sig.song_id = s.id ");
        sb.append("LEFT JOIN movie m on s.movie_id = m.id ");
        sb.append("GROUP BY CONCAT(s.title, m.title) ");

        String myQuery = makeSpecialQuery(sb.toString());

        Query query = em.createNativeQuery(myQuery);
        List<Object[]> tempResults = query.getResultList();

        List<MedalTableSongs> medalTableSongs = new LinkedList<>();

        for (Object[] tempResult : tempResults) {
            MedalTableSongs medalTableSong = new MedalTableSongs();
            Map<Integer, Integer> posMap = new HashMap<>();

            for (int i = 0; i < 20; i++) {
                posMap.put(i + 1, ((BigDecimal) tempResult[i]).intValue());
            }
            medalTableSong.setMedals(posMap);
            medalTableSong.setTotalInList(((BigInteger) tempResult[20]).intValue());
            medalTableSong.setSongTitle((String) tempResult[21]);
            medalTableSong.setMovieTitle((String) tempResult[22]);

            medalTableSongs.add(medalTableSong);
        }
        return medalTableSongs;
    }

    public List<MedalTableArtist> createMedalTableArtistFromDb() {

        StringBuilder sb = new StringBuilder();
        sb.append("a.full_name ");
        sb.append("FROM songs_in_list sig ");
        sb.append("LEFT JOIN song s ON sig.song_id = s.id ");
        sb.append("LEFT JOIN song_has_artist sag on sag.song_id = s.id ");
        sb.append("LEFT JOIN artist a on sag.artist_id = a.id ");
        sb.append("GROUP BY CONCAT(a.full_name) ");

        String myQuery = makeSpecialQuery(sb.toString());

        Query query = em.createNativeQuery(myQuery);
        List<Object[]> tempResults = query.getResultList();

        List<MedalTableArtist> medalTableArtists = new LinkedList<>();

        for (Object[] tempResult : tempResults) {
            MedalTableArtist medalTableArtist = new MedalTableArtist();
            Map<Integer, Integer> posMap = new HashMap<>();

            for (int i = 0; i < 20; i++) {
                posMap.put(i + 1, ((BigDecimal) tempResult[i]).intValue());
            }
            medalTableArtist.setMedals(posMap);
            medalTableArtist.setTotalInList(((BigInteger) tempResult[20]).intValue());
            medalTableArtist.setArtistName((String) tempResult[21]);

            medalTableArtists.add(medalTableArtist);
        }
        return medalTableArtists;
    }

    public List<MedalTableMovie> createMedalTableMoviesFromDb() {

        StringBuilder sb = new StringBuilder();
        sb.append("m.title ");
        sb.append("FROM songs_in_list sig ");
        sb.append("LEFT JOIN song s ON sig.song_id = s.id ");
        sb.append("LEFT JOIN movie m ON m.id = s.movie_id ");
        sb.append("GROUP BY CONCAT(m.title) ");

        String myQuery = makeSpecialQuery(sb.toString());

        Query query = em.createNativeQuery(myQuery);
        List<Object[]> tempResults = query.getResultList();

        List<MedalTableMovie> medalTableMovies = new LinkedList<>();

        for (Object[] tempResult : tempResults) {
            MedalTableMovie medalTableMovie = new MedalTableMovie();
            Map<Integer, Integer> posMap = new HashMap<>();

            for (int i = 0; i < 20; i++) {
                posMap.put(i + 1, ((BigDecimal) tempResult[i]).intValue());
            }
            medalTableMovie.setMedals(posMap);
            medalTableMovie.setTotalInList(((BigInteger) tempResult[20]).intValue());
            medalTableMovie.setMovieTitle((String) tempResult[21]);

            medalTableMovies.add(medalTableMovie);
        }
        return medalTableMovies;
    }
}
