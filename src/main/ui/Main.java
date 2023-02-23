package ui;

import model.ConservationStatus;
import model.Wildlife;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        String threeDigitCode = "";
        for (int i = 0; i < 3; i++) {
            threeDigitCode = threeDigitCode + r.nextInt(10);
        }
        System.out.println(ConservationStatus.CD + Wildlife.EW.get threeDigitCode);


    }
}
