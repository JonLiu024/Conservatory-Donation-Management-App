package model;

import formatters.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


public class ReleasedAnimals {
    private static List<Animal> listOfAnimalsReleased;


    public void ReleaseAnimal(){
        listOfAnimalsReleased = new ArrayList<>();
    }


    public static void addTolist(Animal animal) {
        listOfAnimalsReleased.add(animal);
    }

    public void displayReleasedAnimals() {
        System.out.println("The following animals were sheltered at our facilities and released " +
                "to the wild:");
        for (Animal animal: listOfAnimalsReleased) {
            String addedDate = DateTimeFormatter.toStringLocalDateTime(animal.getAddedDate());
            String releasedDate = DateTimeFormatter.toStringLocalDateTime(animal.getReleasedDate());

            System.out.println(animal.getSpeciesName() + " named " + animal.getNickname() + " was adopted in" +
                    " our conservation sites since " + addedDate + " and released to the wild on " + releasedDate);
        }
    }
}


