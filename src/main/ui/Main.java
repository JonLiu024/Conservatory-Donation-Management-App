package ui;

import formatters.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {

        System.out.println(DateFormatter.toStringLocalDate(LocalDate.now()));
        LocalDate date = DateFormatter.strToLocalDate("1996-06-24");
        System.out.println(DateFormatter.toStringLocalDate(date));

        Pattern pattern = Pattern.compile("20[0-9]{2}-((0[^0^2]|1[0-2])-([0-2][0-9]|3[0-1]))|(02-([0-2][0-9]))");
        Matcher matcher = pattern.matcher("2022-02-28");
        boolean matchFound = matcher.find();
        System.out.println(matchFound);

        System.out.println("ID: DDFD\t" + "Name: d");
        String d = "";
        d = "2";

        System.out.println(d);


    }
}
