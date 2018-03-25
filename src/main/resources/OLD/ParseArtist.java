//package pl.kostrowski.lpmf.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import pl.kostrowski.lpmf.model.Artist;
//import pl.kostrowski.lpmf.repository.ArtistRepository;
//
//import java.util.*;
//
//@Component
//public class ParseArtist {
//
//    private ArtistRepository artistRepository;
//
//    @Autowired
//    public ParseArtist(ArtistRepository artistRepository) {
//        this.artistRepository = artistRepository;
//    }
//
//
//    public List<Artist> makeArtistList(String artists) {
//
//        List<Artist> artistsList = new ArrayList<>();
//        List<Artist> artistsFromMapping = checkArtistMapping(artists);
//
//        if (artistsFromMapping != null) {
//            artistsList = artistsFromMapping;
//        } else {
//
//            String[] split = artists.split("/");
//
//            for (String s : split) {
//                Artist artist = new Artist();
//                String[] split1 = s.split(" ");
//                setArtistDataFromStringArray(artist, split1);
//                artistsList.add(artist);
//            }
//        }
//
//        artistsList = checkIfArtistsExistInDatabaseIfNotSave(artistsList);
//
//        return artistsList;
//    }
//
//    private void setArtistDataFromStringArray(Artist artist, String[] split1) {
//        if (split1.length == 1) {
//            artist.setName("");
//            artist.setSurname(split1[0]);
//        } else {
//            for (int i = 0; i < split1.length - 1; i++) {
//                if (artist.getName() != null) {
//                    artist.setName((artist.getName() + " " + split1[i]).trim());
//                } else {
//                    artist.setName((split1[i]).trim());
//                }
//            }
//            artist.setSurname((split1[split1.length - 1]).trim());
//        }
//
//    }
//
//    private List<Artist> checkIfArtistsExistInDatabaseIfNotSave(List<Artist> artists) {
//
//        List<Artist> artistsList = new ArrayList<>();
//
//        for (Artist artist : artists) {
//
//            Artist artistFromRepository;
//
//            try {
//                artistFromRepository = artistRepository.findArtistByNameAndSurname(artist.getName(), artist.getSurname());
//            } catch (Exception e) {
//                artistFromRepository = null;
//            }
//
//            if (artistFromRepository != null) {
//                artist.setId(artistFromRepository.getId());
//                artistsList.add(artist);
//            } else {
//                artistRepository.save(artist);
//                artistsList.add(artist);
//            }
//        }
//        return artistsList;
//    }
//
//
//    private List<Artist> checkArtistMapping(String artist) {
//
//        Map<String, List<Artist>> mapowanieArtystow = new HashMap<>();
//
//        Artist buenaVistSocialClub = new Artist("","Buena Vista Social Club");
//        List<Artist> buenaVistSocialClubL = new LinkedList<>();
//        buenaVistSocialClubL.add(buenaVistSocialClub);
//
//        Artist theMoodyBlues = new Artist("","The Moody Blues");
//        List<Artist> theMoodyBluesL = new LinkedList<>();
//        theMoodyBluesL.add(theMoodyBlues);
//
//        Artist CD = new Artist("Celine", "Dion");
//        Artist PB = new Artist("Peabo", "Bryson");
//        List<Artist> CDPBL = new LinkedList<>();
//        CDPBL.add(CD);
//        CDPBL.add(PB);
//
//        Artist RiG = new Artist("","Rodrigo & Gabriela");
//        List<Artist> RiGL = new LinkedList<>();
//        RiGL.add(RiG);
//
//        Artist JXL = new Artist("","Junkie XL");
//        List<Artist> JXLL = new LinkedList<>();
//        JXLL.add(JXL);
//
//        Artist RH = new Artist("Heil","Reinhold");
//        Artist JK = new Artist("Johnny","Klimek");
//        Artist TT = new Artist("Tom","Tykwer");
//        List<Artist> RHJKTT = new LinkedList<>();
//        RHJKTT.add(RH);
//        RHJKTT.add(JK);
//        RHJKTT.add(TT);
//
//        mapowanieArtystow.put("Buena Vista Social Club", buenaVistSocialClubL);
//        mapowanieArtystow.put("The Moody Blues", theMoodyBluesL);
//        mapowanieArtystow.put("Celine Dion&Peabo Bryson", CDPBL);
//        mapowanieArtystow.put("Rodrigo & Gabriela", RiGL);
//        mapowanieArtystow.put("Rodrigo y Gabriela", RiGL);
//        mapowanieArtystow.put("Junkie XL", JXLL);
//        mapowanieArtystow.put("Heil Reinhold, Klimek Johnny & Tykwer Tom", RHJKTT);
//        mapowanieArtystow.put("Reinhold / Klimek / Tykwer", RHJKTT);
//
//        if (mapowanieArtystow.containsKey(artist)) {
//            return mapowanieArtystow.get(artist);
//        } else {
//            return null;
//        }
//    }
//}
//
//
//
