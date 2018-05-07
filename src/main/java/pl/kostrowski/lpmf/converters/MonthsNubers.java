package pl.kostrowski.lpmf.converters;

public enum MonthsNubers {

    STYCZNIA(1),
    LUTEGO(2),
    MARCA(3),
    KWIETNIA(4),
    MAJA(5),
    CZERWCA(6),
    LIPCA(7),
    SIERPNIA(8),
    WRZEŚNIA(9),
    PAŹDZIERNIKA(10),
    LISTOPADA(11),
    GRUDNIA(12);

    private int monthNumber;

    MonthsNubers(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }
}
