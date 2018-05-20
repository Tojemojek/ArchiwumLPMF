package pl.kostrowski.lpmf.dictionaries;

public enum PathsToUrls {

    REMOTE_URL("http://www.rmfclassic.pl/lista-przebojow/notowanie/"),
    LOCAL_COPY_OF_HTML("/media/Dane/LPMF/dane/");

    private String path;

    PathsToUrls(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
