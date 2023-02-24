package ui;
import formatters.DateTimeFormatter;
import model.Wildlife;
import model.ConservationSite;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);


    public void displayReleasedAnimals(ConservationSite conservationSite) {
        System.out.println("The following animals were sheltered at conservation sites " + conservationSite.getTypeOfSite()+
                " and released to the wild:");

        for (Wildlife wildlife : conservationSite.getListOfReleasedWildlife()) {
            String addedDate = DateTimeFormatter.toStringLocalDateTime(wildlife.getAddedDate());
            String releasedDate = DateTimeFormatter.toStringLocalDateTime(wildlife.getReleasedDate());

            System.out.println(wildlife.getSpeciesName()  +" "+ wildlife.getWildlifeID() + " was rescued in" +
                    "since " + addedDate + " and released to the wild on " + releasedDate);
        }


}
}

