package model;


import formatters.DateFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.Objects;

//Representing a donor profile that is associated with one donor,
// it has a donor ID, an email address, a profile creation date, the
//total funding amount, list of wildlife and donation records
public class Donor implements Writable {

    private String donorID;                    //donor's ID in the system
    private LocalDate profileCreationDate;      //the date the donor profile is created
    private double totalFundingDonated;         //the total amount that has been donated by the donor
    private List<Donation> recordsOfDonations;    // donor's donation records
    private String emailAddress;        //donor's email


    //REQUIRES: donorID is not null, emailAddress is not null
    //EFFECT: create a donor object; the donorID is set as parameter donorID; the emailAddress is set as parameter
    //emailAddress; listOfWildlife is set as empty arraylist of wildlife, recordsOfDonations is set as empty arraylist
    //of donation, profileCreationDate is set as current localDate; totalFundingDonated is set 0
    public Donor(String donorID, String emailAddress) {
        setDonorID(donorID);
        setEmailAddress(emailAddress);
        recordsOfDonations = new ArrayList<>();
        profileCreationDate = LocalDate.now();
        totalFundingDonated = 0;
    }


    //getters
    public String getDonorID() {

        return this.donorID;
    }

    public LocalDate getProfileCreationDate() {

        return this.profileCreationDate;
    }

    public double getTotalFundingDonated() {

        return this.totalFundingDonated;
    }

    public List<Donation> getRecordsOfDonations() {

        return this.recordsOfDonations;
    }


    public String getEmailAddress() {

        return emailAddress;
    }


    //Setters
    public void setDonorID(String donorID) {

        this.donorID = donorID;
    }

    public void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;
    }

    public void setProfileCreationDate(LocalDate localDate) {
        this.profileCreationDate = localDate;
    }

    public void setTotalFundingDonated(double totalFundingDonated) {
        this.totalFundingDonated = totalFundingDonated;
    }


    public void setRecordsOfDonations(List<Donation> recordsOfDonations) {
        this.recordsOfDonations = recordsOfDonations;
    }

    //REQUIRES: amount > 0,
    //MODIFIES: this, wildlife
    //EFFECT: amountFunded of wildlife is increased by amount, and amount is returned if the wildlife
    // is not fully funded after the increment, otherwise the amountFunded of wildlife is increased
    // to the targetFunding and the increment amount is returned
    public double makeDonation(Wildlife wildlife, double amount) {

        double actualFundingAmt = wildlife.raiseFund(amount);
        Donation donation = new Donation(wildlife.getWildlifeID(), actualFundingAmt);
        recordsOfDonations.add(donation);
        totalFundingDonated += actualFundingAmt;
        wildlife.addDonationToRecords(donation);
        wildlife.addDonorToList(this);
        return actualFundingAmt;
    }



    //REQUIRES: d is not null
    //MODIFIES: this
    //EFFECT: donation d is added to recordsOfDonations if it has not been previously added,
    // and if the donation is associated with this donor
    public void addDonationToList(Donation d) {
        if (!recordsOfDonations.contains(d)) {
            recordsOfDonations.add(d);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("donorID", donorID);
        jsonObject.put("profileCreationDate", DateFormatter.toStringLocalDate(profileCreationDate));
        jsonObject.put("totalFundingDonated", totalFundingDonated);
        jsonObject.put("recordsOfDonations", recordsOfDonationsToJson());
        jsonObject.put("emailAddress", emailAddress);

        return jsonObject;
    }


    //EFFECT: returns donations made by this donor as a JSON array
    private JSONArray recordsOfDonationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Donation d: recordsOfDonations) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }


}