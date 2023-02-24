package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ConservationSite {
    private List<Wildlife> listOfWildlife;
    private List<Wildlife> listOfReleasedWildlife;
    private List<Donor> listOfDonors;
    private double totalTargetFunding;
    private double totalFundingRaised;






    public ConservationSite(){
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        listOfWildlife = new ArrayList<>();
        listOfReleasedWildlife = new ArrayList<>();
        listOfDonors = new ArrayList<>();

    }


    public void appendlistOfDonors() {
        for (Wildlife wildlife: listOfWildlife) {
            for (Donor donor: wildlife.getListOfDonors()) {
                if (!listOfDonors.contains(donor)) {
                    listOfDonors.add(donor);
                }
            }
        }
    }


    //getters
    public List<Wildlife> getListOfWildlife() {
        return listOfWildlife;
    }
    public List<Wildlife> getListOfReleasedWildlife() {return listOfReleasedWildlife;}
    public List<Donor> getListOfDonors() {return listOfDonors;}
    public double getTotalTargetFunding() {
        return totalTargetFunding;
    }
    public double getTotalFundingRaised() {
        return totalFundingRaised;
    }

    //setters




    public void addWildlife(Wildlife wildlife) {
        if (!listOfWildlife.contains(wildlife)) {
            listOfWildlife.add(wildlife);
        };
    }

    public void removeWildlife(Wildlife wildlife) {
        if (listOfWildlife.contains(wildlife)) {
            listOfWildlife.remove(wildlife);
        }
    }

    public void releaseWildlife(Wildlife wildlife) {
        removeWildlife(wildlife);
        wildlife.setFundingMetDate(LocalDate.now());
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
