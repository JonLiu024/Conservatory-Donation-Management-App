package formatters;

import java.time.LocalDateTime;

public class DateTimeFormatter {

    public static String toStringLocalDateTime(LocalDateTime localDateTime) {
        java.time.format.DateTimeFormatter dtf;
        dtf = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        return dtf.format(localDateTime);


    }
}
