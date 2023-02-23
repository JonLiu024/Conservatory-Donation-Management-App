package ui;
import formatters.DateTimeFormatter;
import model.Wildlife;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);


    public void displayReleasedAnimals() {
        System.out.println("The following animals were sheltered at our facilities and released " +
                "to the wild:");
        for (Wildlife wildlife : listOfAnimalsReleased) {
            String addedDate = DateTimeFormatter.toStringLocalDateTime(wildlife.getAddedDate());
            String releasedDate = DateTimeFormatter.toStringLocalDateTime(wildlife.getReleasedDate());

            System.out.println(wildlife.getSpeciesName() + " named " + wildlife.getWildlifeID() + " was adopted in" +
                    " our conservation sites since " + addedDate + " and released to the wild on " + releasedDate);
        }


}
