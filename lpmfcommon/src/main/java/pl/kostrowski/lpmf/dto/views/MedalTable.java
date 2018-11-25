package pl.kostrowski.lpmf.dto.views;

import lombok.Data;

import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class MedalTable {

    @Transient
    protected Integer totalInList;

    @Transient
    protected Map<Integer, Integer> medals = new HashMap<>();

    public abstract void prepare();

}
