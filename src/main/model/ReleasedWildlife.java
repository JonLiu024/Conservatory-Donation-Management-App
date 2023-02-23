package model;

import formatters.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


public class ReleasedWildlife {
    private static List<Wildlife> listOfAnimalsReleased;


    public void ReleaseAnimal(){
        listOfAnimalsReleased = new ArrayList<>();
    }


    public static void addTolist(Wildlife wildlife) {
        listOfAnimalsReleased.add(wildlife);
    }

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
}


