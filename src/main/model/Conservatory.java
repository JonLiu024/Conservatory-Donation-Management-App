package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

//Represents a conservation site that holds numbers of injured wildlife that are recovering
//fully funded and a list of donors
public class Conservatory implements Writable {
    private List<Wildlife> wildlifeListNotFullyFunded; //list of admitted wildlife that have not been fully funded yet
    private List<Wildlife> wildlifeListFullyFunded;   // list of admitted wildlife that have been fully funded
    private List<Donor> listOfDonors;   //list of donors profiles
    private double totalTargetFunding;  //the total amount of funding to be raised
    private double totalFundingRaised;  //the total amount of funding that has been raised
    private String name; //the conservatory's name


    //EFFECT: create a Conservatory object; the totalTargetFunding and totalFundingRaised are set zero,
    // listOfDonors is set as an empty donor arraylists;
    // wildlifeListNotFullyFunded is set as an empty wildlife arraylist
    // wildlifeListFullyFunded is set as an empty wildlife arraylist
    public Conservatory() {
        this.totalTargetFunding = 0;
        this.totalFundingRaised = 0;
        wildlifeListNotFullyFunded = new ArrayList<>();
        wildlifeListFullyFunded = new ArrayList<>();
        listOfDonors = new ArrayList<>();
        name = "Wildlife Conservation Facility";
    }


    //getters
    public List<Wildlife> getListOfWildlifeNotFullyFunded() {

        return wildlifeListNotFullyFunded;
    }

    public List<Wildlife> getWildlifeListFullyFunded() {

        return wildlifeListFullyFunded;
    }

    public List<Donor> getListOfDonors() {
        return listOfDonors;
    }

    public double getTotalTargetFunding() {

        return totalTargetFunding;
    }

    public double getTotalFundingRaised() {

        return totalFundingRaised;
    }

    public String getName() {
        return this.name;
    }


    //setters

    public void setTotalFundingRaised(double totalFundingRaised) {
        this.totalFundingRaised = totalFundingRaised;
    }

    public void setListOfDonors(List<Donor> listOfDonors) {
        this.listOfDonors = listOfDonors;
    }

    public List<Wildlife> getWildlifeListNotFullyFunded() {
        return wildlifeListNotFullyFunded;
    }

    public void setTotalTargetFunding(double totalTargetFunding) {
        this.totalTargetFunding = totalTargetFunding;
    }

    public void setWildlifeListFullyFunded(List<Wildlife> wildlifeListFullyFunded) {
        this.wildlifeListFullyFunded = wildlifeListFullyFunded;
    }

    public void setWildlifeListNotFullyFunded(List<Wildlife> wildlifeListNotFullyFunded) {
        this.wildlifeListNotFullyFunded = wildlifeListNotFullyFunded;
    }


    //REQUIRES: d is not null
    //MODIFIES: this
    //EFFECT: donor d is added into the listOfDonors if it has not been previously added
    public void addDonor(Donor d) {
        if (!listOfDonors.contains(d)) {
            listOfDonors.add(d);
            String description = "A donor profile (Donor ID: " + d.getDonorID() + ") is created and added into "
                    + "the conservatory";
            Event donorProfileCreationEvent = new Event(description);
            EventLog.getInstance().logEvent(donorProfileCreationEvent);
        }
    }


    //REQUIRES: wildlifeListNotFullyFunded and wildlifeListFullyFunded do not contain same element (wildlife objects)
    //EFFECT: returns an array list containing all fully funded wildlife and all non-fully funded wildlife
    public List<Wildlife> getListOfAllWL() {
        List<Wildlife> listOfAllWL = new ArrayList<>();
        listOfAllWL.addAll(wildlifeListNotFullyFunded);
        listOfAllWL.addAll(wildlifeListFullyFunded);
        return listOfAllWL;
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
            String description = "A wildlife (" + wildlife.getWildlifeID() + ") is added to the Conservatory ";
            Event addWildlifeEvent = new Event(description);
            EventLog.getInstance().logEvent(addWildlifeEvent);
        }
    }

    //REQUIRES: wildlife is not null
    //MODIFIES: this
    //EFFECT: removes wildlife from wildlifeListNotFullyFunded and add wildlife to
    //wildlifeListFullyFunded; dateFullyFunded is set as current localDate
    public void moveWildlifeToFullyFundedList(Wildlife wildlife) {
        wildlife.setDateFullyFunded(LocalDate.now());

        wildlife.setIsFullyFunded(true);
        String description2 = "A wildlife (" + wildlife.getWildlifeID() + ") becomes fully funded";
        Event fullyFundedEvent = new Event(description2);
        EventLog.getInstance().logEvent(fullyFundedEvent);

        wildlifeListNotFullyFunded.remove(wildlife);
        String description1 = "A wildlife (" + wildlife.getWildlifeID() + ") is removed from "
                + "the list of wildlife for funding";
        Event wildlifeRemovalEvent = new Event(description1);
        EventLog.getInstance().logEvent(wildlifeRemovalEvent);

        wildlifeListFullyFunded.add(wildlife);
        String description3 = "A wildlife (" + wildlife.getWildlifeID() + ") is added to the list of fully funded"
                + " wildlife";
        Event addingEvent = new Event(description3);
        EventLog.getInstance().logEvent(addingEvent);
    }

    //EFFECT: calculates and returns the total funds that were raised from the donations; The total funding raised is
    //calculated as the sum of total raised funding for all wildlife in the conservation site
    public double fundsRaisedCalculator() {
        int totalRaisedAmount = 0;
        for (Wildlife wildlife : getListOfAllWL()) {
            totalRaisedAmount += wildlife.getAmountFunded();
        }
        return totalRaisedAmount;
    }

    //MODIFIES: this
    //EFFECT: the totalFundingRaised is updated, set by the fundsRasiedCalculator
    public void updateRaisedFunding() {
        totalFundingRaised = fundsRaisedCalculator();
    }




    //EFFECT: calculates and returns the total target funds of all wildlife; The total target funding is calculated
    //as the sum of target funding for all wildlife in the conservation site
    public double targetFundingCalculator() {
        double totalTargetFunding = 0;
        for (Wildlife wildlife : getListOfAllWL()) {
            totalTargetFunding += wildlife.getTargetFunding();
        }
        return totalTargetFunding;
    }

    //REQUIRES: listOfDonors.size() > 0
    //EFFECT: returns the list of donor(s) who have(has) made the highest amount of donations to the conservation site
    public List<Donor> mostGenerousDonor() {
        List<Donor> donorList = new ArrayList<>();
        double highestDonation = listOfDonors.get(0).getTotalFundingDonated();
        for (Donor d: listOfDonors) {
            if (d.getTotalFundingDonated() > highestDonation) {
                donorList.clear();
                donorList.add(d);
                highestDonation = d.getTotalFundingDonated();
            } else if (d.getTotalFundingDonated() == highestDonation) {
                donorList.add(d);
            }
        }
        String description = "The list of the conservatory's most generous donors is created";
        Event event = new Event(description);
        EventLog.getInstance().logEvent(event);
        return donorList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wildlifeListNotFullyFunded", wildlifeListNotFullyFundedToJson());
        jsonObject.put("wildlifeListFullyFunded", wildlifeListFullyFundedToJson());
        jsonObject.put("listOfDonor", listOfDonorToJson());
        jsonObject.put("totalTargetFunding", totalTargetFunding);
        jsonObject.put("totalFundingRaised", totalFundingRaised);
        jsonObject.put("name", name);
        return jsonObject;
    }



    //EFFECT: returns wildlife that are not fully funded as a JSON array
    private JSONArray wildlifeListNotFullyFundedToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Wildlife wl: wildlifeListNotFullyFunded) {
            jsonArray.put(wl.toJson());
        }
        return jsonArray;
    }



    //EFFECT: returns wildlife that are fully funded as a JSON array
    private JSONArray wildlifeListFullyFundedToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Wildlife wl: wildlifeListFullyFunded) {
            jsonArray.put(wl.toJson());
        }
        return jsonArray;
    }


    //EFFECT: returns all donors that have made donations as a JSON array
    private JSONArray listOfDonorToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Donor d: listOfDonors) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }
}
