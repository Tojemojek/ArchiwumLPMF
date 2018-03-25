package pl.kostrowski.lpmf.service;

public enum Path {

    PATH_FOR_REMOTE_HTML("http://www.rmfclassic.pl/lista-przebojow/notowanie/"),
    PATH_FOR_LOCAL_HTML("/media/ko/Dane/Temp/lista/dane/");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
