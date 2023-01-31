package model;

import java.util.ArrayList;

public class ConservationSite {
    private ArrayList<Animal> shelteredAnimals;
    private double totalTargetFunding;
    private double totalFundingRaised;



    public void AddShelteredAnimal(Animal animal) {
        shelteredAnimals.add(animal);
    }

    public void removeAnimal(Animal animal){
        shelteredAnimals.remove(animal);
    }

    public double FundsRaisedCalculator(){
        int totalRaisedAmount = 0;
        for (Animal animal: shelteredAnimals){
            totalRaisedAmount += animal.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    public double TargetFundingCalculator(){
        int totalTargetAmount = 0;
        for (Animal animal: shelteredAnimals){
            totalTargetFunding += animal.getTargetFunding();
        }
        return totalTargetAmount;
    }

}
