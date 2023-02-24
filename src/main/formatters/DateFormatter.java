package formatters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {



    public static String toStringLocalDate(LocalDate localDate) {
        java.time.format.DateTimeFormatter dtf;
        dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(localDate);
    }


    public static LocalDate strToLocalDate(String strTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strTime, formatter);
        return date;
    }
}
