package model;

import java.util.ArrayList;

public class ConservationSite {
    private ArrayList<Animal> animalsInSite;
    private double totalTargetFunding;
    private double totalFundingRaised;
    private String typeOfSite;

    public ConservationSite(String typeOfSite){
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        ArrayList<Animal> ListOfAnimals= new ArrayList<>();
        this.typeOfSite = typeOfSite;

    }



    public void AddAnimal(Animal animal) {
        if (!animalsInSite.contains(animal)) {
            animalsInSite.add(animal);
        };
    }

    public void removeAnimal(Animal animal) {
        if (animalsInSite.contains(animal)) {
            removeAnimal(animal);
        }
    }



    public double FundsRaisedCalculator(){
        int totalRaisedAmount = 0;
        for (Animal animal: animalsInSite){
            totalRaisedAmount += animal.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    public double TargetFundingCalculator(){
        int totalTargetAmount = 0;
        for (Animal animal: animalsInSite){
            totalTargetFunding += animal.getTargetFunding();
        }
        return totalTargetAmount;
    }




    public void releaseAnimalsToWild(Animal animal){

}
}
