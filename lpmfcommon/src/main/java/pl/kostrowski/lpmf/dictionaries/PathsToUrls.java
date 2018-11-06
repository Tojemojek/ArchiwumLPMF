package pl.kostrowski.lpmf.dictionaries;

public enum PathsToUrls {

    REMOTE_URL("http://www.rmfclassic.pl/lista-przebojow/notowanie/");

    private String path;

    PathsToUrls(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
