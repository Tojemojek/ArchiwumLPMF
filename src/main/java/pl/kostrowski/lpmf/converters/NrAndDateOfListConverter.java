package pl.kostrowski.lpmf.converters;


import org.springframework.stereotype.Service;
import pl.kostrowski.lpmf.model.ListInfo;

import java.time.LocalDate;

@Service
public class NrAndDateOfListConverter {

    public ListInfo convert (String nrAndDateOfList){

        ListInfo listInfo = new ListInfo();

        String[] split = nrAndDateOfList.split(" ");

        Integer noOfList = Integer.parseInt(split[2]);
        int day = Integer.parseInt(split[5]);
        int month = MonthsNubers.valueOf(split[6].toUpperCase()).getMonthNumber();
        int year = Integer.parseInt(split[7]);

        LocalDate date = LocalDate.of(year,month,day);

        listInfo.setDate(date);
        listInfo.setNoOfList(noOfList);

        return listInfo;

    }
}
