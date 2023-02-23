package model;

import java.util.List;
import java.util.ArrayList;

public class ConservationSite {
    private List<Wildlife> listOfWildlife;
    private List<Wildlife> listOfReleasedWildlife;
    private double totalTargetFunding;
    private double totalFundingRaised;
    private TypeConservationSite typeOfSite;






    public ConservationSite(TypeConservationSite typeOfSite){
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        List<Wildlife> listOfWildlife = new ArrayList<>();
        List<Wildlife> listOfReleasedWildlife = new ArrayList<>();
        this.typeOfSite = typeOfSite;

    }


    //getters
    public List<Wildlife> getListOfWildlife() {
        return listOfWildlife;
    }
    public List<Wildlife> getListOfReleasedWildlife() {return listOfReleasedWildlife;}
    public double getTotalTargetFunding() {
        return totalTargetFunding;
    }
    public double getTotalFundingRaised() {
        return totalFundingRaised;
    }
    public TypeConservationSite getTypeOfSite() {
        return typeOfSite;
    }

    //setters




    public void AddAnimal(Wildlife wildlife) {
        if (!listOfWildlife.contains(wildlife)) {
            listOfWildlife.add(wildlife);
        };
    }

    public void removeAnimal(Wildlife wildlife) {
        if (listOfWildlife.contains(wildlife)) {
            removeAnimal(wildlife);
        }
    }

    public void releaseWildlife(Wildlife wildlife) {
        removeAnimal(wildlife);
        listOfReleasedWildlife.add(wildlife);
    }



    public double FundsRaisedCalculator(){
        int totalRaisedAmount = 0;
        for (Wildlife wildlife : listOfWildlife){
            totalRaisedAmount += wildlife.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    public double TargetFundingCalculator(){
        int totalTargetAmount = 0;
        for (Wildlife wildlife : listOfWildlife){
            totalTargetFunding += wildlife.getTargetFunding();
        }
        return totalTargetAmount;
    }

}
