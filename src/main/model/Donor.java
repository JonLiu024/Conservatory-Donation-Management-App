package model;

import Exception.*;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Donor {
    private String donorID;
    private LocalDate profileCreationDate;
    private double totalFundingDonated;
    private List<Wildlife> listOfWildlife;
    private List<Donation> recordsOfDonations;
    private String emailAddress;





    public Donor(String donorID, String emailAddress) {
        this.donorID = donorID;
        this.emailAddress = emailAddress;
        listOfWildlife = new ArrayList<>();
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

    public List<Wildlife> getListOfWildlife() {

        return this.listOfWildlife;
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




    public void makeDonation(Wildlife wildlife, double amount) {

        Donation donation = new Donation(wildlife, this, amount);
        recordsOfDonations.add(donation);
        addWLToList(wildlife);
        totalFundingDonated += amount;

        wildlife.raiseFund(amount);
        wildlife.addDonorToTheList(this);
        wildlife.addDonationToRecords(donation);
    }

    public void addWLToList(Wildlife wildlife) {
        if (!listOfWildlife.contains(wildlife)) {
            listOfWildlife.add(wildlife);
        }
    }

    public void addDonationToList(Donation d) {
        if (!recordsOfDonations.contains(d)) {
            recordsOfDonations.add(d);
        }
    }





}
