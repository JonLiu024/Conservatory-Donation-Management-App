package ui;

import formatters.DateTimeFormatter;
import model.ConservationStatus;
import model.Wildlife;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println(DateTimeFormatter.toStringLocalDateTime(LocalDateTime.now()));

    }
}
