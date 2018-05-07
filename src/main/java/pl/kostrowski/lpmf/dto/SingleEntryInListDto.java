package pl.kostrowski.lpmf.dto;

public class SingleEntryInListDto {

    String nrIDataListy;
    String position;
    String fullArtist;
    String fullSongTitle;
    String fullMovieTitle;
    String coverLink;


    public SingleEntryInListDto() {
    }

    public SingleEntryInListDto(String nrIDataListy, String position, String fullArtist, String fullSongTitle, String fullMovieTitle, String coverLink) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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
