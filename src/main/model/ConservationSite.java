package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ConservationSite {
    private List<Wildlife> wildlifeListNotFullyFunded;
    private List<Wildlife> wildlifeListFullyFunded;
    private List<Donor> listOfDonors;
    private double totalTargetFunding;
    private double totalFundingRaised;






    public ConservationSite() {
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        wildlifeListNotFullyFunded = new ArrayList<>();
        wildlifeListFullyFunded = new ArrayList<>();
        listOfDonors = new ArrayList<>();

    }


    public void updateListOfDonors() {
        for (Wildlife wildlife: getListOfAllWL()) {
            for (Donor donor: wildlife.getListOfDonors()) {
                if (!listOfDonors.contains(donor)) {
                    listOfDonors.add(donor);
                }
            }
        }
    }


    //getters
    public List<Wildlife> getListOfWildlifeNotFullyFunded() {
        return wildlifeListNotFullyFunded;
    }

    public List<Wildlife> getWildlifeListFullyFunded() {
        return wildlifeListFullyFunded;
    }

    public List<Wildlife> getListOfAllWL() {
        wildlifeListNotFullyFunded.addAll(wildlifeListFullyFunded);
        return wildlifeListNotFullyFunded;
    }

    public List<Donor> getListOfDonors() {
        updateListOfDonors();
        return listOfDonors;
    }

    public double getTotalTargetFunding() {

        return totalTargetFunding;
    }

    public double getTotalFundingRaised() {

        return totalFundingRaised;
    }

    //setters


    public void addDonor(Donor d) {
        if (!listOfDonors.contains(d)) {
            listOfDonors.add(d);
        }
    }

    public List<String> getWildlifeIDList() {
        List<String> wildlifeIDList = new ArrayList<>();
        for (Wildlife wl: wildlifeListNotFullyFunded) {
            wildlifeIDList.add(wl.getWildlifeID());
        }
        return wildlifeIDList;
    }


    public List<String> getDonorIDList() {
        List<String> donorIDList = new ArrayList<>();
        for (Donor d: listOfDonors) {
            donorIDList.add(d.getDonorID());
        }
        return donorIDList;
    }



    public void addWildlife(Wildlife wildlife) {
        if (!wildlifeListNotFullyFunded.contains(wildlife)) {
            wildlifeListNotFullyFunded.add(wildlife);
        }
    }


    public void moveWildlifeToFullyFundedList(Wildlife wildlife) {
        wildlifeListNotFullyFunded.remove(wildlife);
        wildlife.setFundingMetDate(LocalDate.now());
        wildlifeListFullyFunded.add(wildlife);
    }


    public double fundsRaisedCalculator() {
        int totalRaisedAmount = 0;
        for (Wildlife wildlife : wildlifeListNotFullyFunded) {
            totalRaisedAmount += wildlife.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    public double targetFundingCalculator() {
        int totalTargetAmount = 0;
        for (Wildlife wildlife : wildlifeListNotFullyFunded) {
            totalTargetFunding += wildlife.getTargetFunding();
        }
        return totalTargetAmount;
    }

}
