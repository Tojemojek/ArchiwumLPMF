package pl.kostrowski.lpmf.dto;

public class SingleLpmfDto {

    private String nrIDataListy;
    private Integer position;
    private String fullArtist;
    private String fullSongTitle;
    private String fullMovieTitle;
    private String coverLink;


    public SingleLpmfDto() {
    }

    public SingleLpmfDto(String nrIDataListy, Integer position, String fullArtist, String fullSongTitle, String fullMovieTitle, String coverLink) {
        this.nrIDataListy = nrIDataListy;
        this.position = position;
        this.fullArtist = fullArtist;
        this.fullSongTitle = fullSongTitle;
        this.fullMovieTitle = fullMovieTitle;
        this.coverLink = coverLink;
    }

    public String getNrAndDateOfList() {
        return nrIDataListy;
    }

    public void setNrIDataListy(String nrIDataListy) {
        this.nrIDataListy = nrIDataListy;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getFullArtist() {
        return fullArtist;
    }

    public void setFullArtist(String fullArtist) {
        this.fullArtist = fullArtist;
    }

    public String getFullSongTitle() {
        return fullSongTitle;
    }

    public void setFullSongTitle(String fullSongTitle) {
        this.fullSongTitle = fullSongTitle;
    }

    public String getFullMovieTitle() {
        return fullMovieTitle;
    }

    public void setFullMovieTitle(String fullMovieTitle) {
        this.fullMovieTitle = fullMovieTitle;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }
}
