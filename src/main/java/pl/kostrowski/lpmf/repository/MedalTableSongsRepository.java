package pl.kostrowski.lpmf.repository;

import org.springframework.stereotype.Repository;
import pl.kostrowski.lpmf.dto.MedalTableSongs;

import javax.persistence.*;
import java.util.List;

@Repository
public class MedalTableSongsRepository {

    @PersistenceContext
    private EntityManager em;

    public List<MedalTableSongs> createMedalTableDataFromDb() {
        Query query = em.createNativeQuery(
                "SELECT " +
                        "    sum(case when sig.pos = 1 then 1 else 0 end) as poz1, " +
                        "    sum(case when sig.pos = 2 then 1 else 0 end) as poz2, " +
                        "    sum(case when sig.pos = 3 then 1 else 0 end) as poz3, " +
                        "    sum(case when sig.pos = 4 then 1 else 0 end) as poz4, " +
                        "    sum(case when sig.pos = 5 then 1 else 0 end) as poz5, " +
                        "    sum(case when sig.pos = 6 then 1 else 0 end) as poz6, " +
                        "    sum(case when sig.pos = 7 then 1 else 0 end) as poz7, " +
                        "    sum(case when sig.pos = 8 then 1 else 0 end) as poz8, " +
                        "    sum(case when sig.pos = 9 then 1 else 0 end) as poz9, " +
                        "    sum(case when sig.pos = 10 then 1 else 0 end) as poz10, " +
                        "    sum(case when sig.pos = 11 then 1 else 0 end) as poz11, " +
                        "    sum(case when sig.pos = 12 then 1 else 0 end) as poz12, " +
                        "    sum(case when sig.pos = 13 then 1 else 0 end) as poz13, " +
                        "    sum(case when sig.pos = 14 then 1 else 0 end) as poz14, " +
                        "    sum(case when sig.pos = 15 then 1 else 0 end) as poz15, " +
                        "    sum(case when sig.pos = 16 then 1 else 0 end) as poz16, " +
                        "    sum(case when sig.pos = 17 then 1 else 0 end) as poz17, " +
                        "    sum(case when sig.pos = 18 then 1 else 0 end) as poz18, " +
                        "    sum(case when sig.pos = 19 then 1 else 0 end) as poz19, " +
                        "    sum(case when sig.pos = 20 then 1 else 0 end) as poz20, " +
                        "    count(sig.pos) as totalInList, " +
                        "    s.title as songTitle, " +
                        "    m.title as movieTitle " +
                        "FROM" +
                        "    songs_in_list sig " +
                        "LEFT JOIN " +
                        "    song s ON sig.song_id = s.id " +
                        "LEFT JOIN " +
                        "movie m on s.movie_id = m.id " +
                        "GROUP BY CONCAT(s.title, m.title) " +
                        "ORDER BY " +
                        "poz1 desc, " +
                        "poz2 desc, " +
                        "poz3 desc, " +
                        "poz4 desc, " +
                        "poz5 desc, " +
                        "poz6 desc, " +
                        "poz7 desc, " +
                        "poz8 desc, " +
                        "poz9 desc, " +
                        "poz10 desc, " +
                        "poz11 desc, " +
                        "poz12 desc, " +
                        "poz13 desc, " +
                        "poz14 desc, " +
                        "poz15 desc, " +
                        "poz16 desc, " +
                        "poz17 desc, " +
                        "poz18 desc," +
                        "poz19 desc," +
                        "poz20 desc",
                "medalTableMapping");
        return query.getResultList();
    }
}
