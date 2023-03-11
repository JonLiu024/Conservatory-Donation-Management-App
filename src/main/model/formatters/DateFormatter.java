package model.formatters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//Representing a date formatter to convert LocalDate to string or vice versa
public class DateFormatter {


    //EFFECT: construct a dateformatter oject
    public DateFormatter() {

    }




    //MODIFIES: this
    //EFFECT: converts the localDate into a string in the format of "yyyy-MM-dd" and returns it
    public static String toStringLocalDate(LocalDate localDate) {
        if (localDate == null) {
            return "null";
        } else {
            java.time.format.DateTimeFormatter dtf;
            dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return dtf.format(localDate);
        }
    }

    //REQUIRES: strDate is in the form of "yyyy-MM-dd"
    //MODIFIES: this
    //EFFECT: converts a string into a LocalDate and returns it
    public static LocalDate strToLocalDate(String strDate) {
        if (strDate.equals("null")) {
            return null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(strDate, formatter);
        }
    }
}
