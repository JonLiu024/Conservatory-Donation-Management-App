package model;

import java.util.ArrayList;
import java.util.List;


public class OutInTheWild {
    private List<Animal> listOfAnimalsReleased;


    public void ReleaseAnimal(){
        listOfAnimalsReleased = new ArrayList<>();
    }


    public void addTolist(Animal animal) {
        listOfAnimalsReleased.add(animal);
    }
}
