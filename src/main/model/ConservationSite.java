package model;

import java.util.ArrayList;

public class ConservationSite {
    private ArrayList<Wildlife> animalsInSite;
    private double totalTargetFunding;
    private double totalFundingRaised;
    private TypeConservationSite typeOfSite;

    public ConservationSite(TypeConservationSite typeOfSite){
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        ArrayList<Wildlife> listOfWildlives = new ArrayList<>();
        this.typeOfSite = typeOfSite;

    }


    public void AddAnimal(Wildlife wildlife) {
        if (!animalsInSite.contains(wildlife)) {
            animalsInSite.add(wildlife);
        };
    }

    public void removeAnimal(Wildlife wildlife) {
        if (animalsInSite.contains(wildlife)) {
            removeAnimal(wildlife);
        }
    }



    public double FundsRaisedCalculator(){
        int totalRaisedAmount = 0;
        for (Wildlife wildlife : animalsInSite){
            totalRaisedAmount += wildlife.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    public double TargetFundingCalculator(){
        int totalTargetAmount = 0;
        for (Wildlife wildlife : animalsInSite){
            totalTargetFunding += wildlife.getTargetFunding();
        }
        return totalTargetAmount;
    }

}
