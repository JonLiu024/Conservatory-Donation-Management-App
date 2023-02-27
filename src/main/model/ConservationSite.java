package model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

//Represents a conservation site that holds numbers of injured wildlife that are recovering
//fully funded and a list of donors
public class ConservationSite {
    private List<Wildlife> wildlifeListNotFullyFunded; //list of admitted wildlife that have not been fully funded yet
    private List<Wildlife> wildlifeListFullyFunded;   // list of admitted wildlife that have been fully funded
    private List<Donor> listOfDonors;   //list of donors profiles that have made donations to the wildlife
    private double totalTargetFunding;  //the total amount of funding to be raised
    private double totalFundingRaised;  //the total amount of funding that has been raised


    //EFFECT: create a conservation site object; the totalTargetFunding and totalFundingRaised are set zero,
    // listOfDonors is set as an empty donor arraylists;
    // wildlifeListNotFullyFunded is set as an empty wildlife arraylist
    // wildlifeListFullyFunded is set as an empty wildlife arraylist
    public ConservationSite() {
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        wildlifeListNotFullyFunded = new ArrayList<>();
        wildlifeListFullyFunded = new ArrayList<>();
        listOfDonors = new ArrayList<>();

    }


    //getters
    public List<Wildlife> getListOfWildlifeNotFullyFunded() {

        return wildlifeListNotFullyFunded;
    }

    public List<Wildlife> getWildlifeListFullyFunded() {

        return wildlifeListFullyFunded;
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



    //REQUIRES: d is not null
    //MODIFIES: this
    //EFFECT: donor d is added into the listOfDonors if it has not been previously added
    public void addDonor(Donor d) {
        if (!listOfDonors.contains(d)) {
            listOfDonors.add(d);
        }
    }


    //REQUIRES: wildlifeListNotFullyFunded and wildlifeListFullyFunded do not contain same element (wildlife objects)
    //EFFECT: returns an array list containing all fully funded wildlife and all non-fully funded wildlife
    public List<Wildlife> getListOfAllWL() {
        List<Wildlife> listOfAllWL = new ArrayList<>();
        for (Wildlife wildlife : wildlifeListNotFullyFunded) {
            if (!listOfAllWL.contains(wildlife)) {
                listOfAllWL.add(wildlife);
            }
        }
        for (Wildlife wildlife : wildlifeListFullyFunded) {
            if (!listOfAllWL.contains(wildlife)) {
                listOfAllWL.add(wildlife);
            }
        }
        return listOfAllWL;
    }


    //MODIFIES: this
    //EFFECT: update the list of donors based on the most recent donation records; add donors to the list of donors
    //if they have not been previously added
    public void updateListOfDonors() {
        for (Wildlife wildlife : getListOfAllWL()) {
            for (Donor donor : wildlife.getListOfDonors()) {
                if (!listOfDonors.contains(donor)) {
                    listOfDonors.add(donor);
                }
            }
        }
    }


    //REQUIRES:listOfDonors does not contain duplicate elements
    //EFFECT: returns an arraylist of strings that contains IDs of all donors (non-duplicate) of the conservation site
    public List<String> getDonorIDList() {
        List<String> donorIDList = new ArrayList<>();
        for (Donor d : listOfDonors) {
            donorIDList.add(d.getDonorID());
        }
        return donorIDList;
    }


    //SPECIFIES: wildlife is not null
    //MODIFIES: this
    //EFFECT: add a wildlife into the wildlifeListNotFullyFunded if it has not been previously added; total
    //totalTargetFunding is increased by wildlife's targetFunding
    public void addWildlife(Wildlife wildlife) {
        if (!wildlifeListNotFullyFunded.contains(wildlife)) {
            wildlifeListNotFullyFunded.add(wildlife);
            totalTargetFunding += wildlife.getTargetFunding();
        }
    }

    //REQUIRES: wildlife is not null
    //MODIFIES: this
    //EFFECT: removes wildlife from wildlifeListNotFullyFunded and add wildlife to
    //wildlifeListFullyFunded; dateFullyFunded is set as current localDate
    public void moveWildlifeToFullyFundedList(Wildlife wildlife) {
        wildlifeListNotFullyFunded.remove(wildlife);
        wildlife.setDateFullyFunded(LocalDate.now());
        wildlifeListFullyFunded.add(wildlife);
    }

    //MODIFIES: this
    //EFFECT: calculates and returns the total funds that were raised from the donations; The total funding raised is
    //calculated as the sum of total raised funding for all wildlife in the conservation site
    public double fundsRaisedCalculator() {
        int totalRaisedAmount = 0;
        for (Wildlife wildlife : getListOfAllWL()) {
            totalRaisedAmount += wildlife.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    //MODIFIES:this
    //EFFECT: calculates and returns the total target funds of all wildlife; The total target funding is calculated
    //as the sum of target funding for all wildlife in the conservation site
    public double targetFundingCalculator() {
        double totalTargetFunding = 0;
        for (Wildlife wildlife : getListOfAllWL()) {
            totalTargetFunding += wildlife.getTargetFunding();
        }
        return totalTargetFunding;
    }
}
