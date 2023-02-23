package model;

import Exception.*;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Donor {
    private String donorID;
    private LocalDateTime profileCreationDate;
    private double totalFundingDonated;
    private List<Wildlife> listOfWildlife;
    private double donationImpact;
    private List<Donation> recordsOfDonations;
    private String emailAddress;





    public Donor(String donorID) {
        this.donorID = donorID;
        this.totalFundingDonated = totalFundingDonated;
        listOfWildlife = new ArrayList<>();
        recordsOfDonations = new ArrayList<>();
        profileCreationDate = LocalDateTime.now();
        totalFundingDonated = 0;


    }


    //getters
    public String getDonorID() {
        return this.donorID;
    }
    public LocalDateTime getAccountCreationDate(){
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

    public double getDonationImpact(){
        return donationImpact;
    }
    public String getEmailAddress() {return emailAddress;}



    //Setters
    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}




    public void makeDonation(Wildlife wildlife, double amount) throws InvalidAmountDonationException {
        if (amount <= 0) {
            throw new InvalidAmountDonationException("Please enter a valid selection");
        }
        addAnimalToList(wildlife);
        wildlife.raiseFund(amount);
        totalFundingDonated += amount;
        recordsOfDonations.add(new Donation(wildlife, this, amount, LocalDateTime.now()));
        wildlife.addDonorToTheList(this);

    }

    public void addAnimalToList(Wildlife wildlife){
        if (!listOfWildlife.contains(wildlife)) {
            listOfWildlife.add(wildlife);
        }
    }

    public void printAllDonationsRecords() {
        for (Donation donation: recordsOfDonations) {
            String dateCreated = formatters.DateTimeFormatter.toStringLocalDateTime(donation.getDateTimeDonationCreated());
            System.out.println(donation.getDonor().getDonorID() +" donated " + donation.getAmount() + " to " +
                    donation.getAnimal().getWildlifeID() + " on " + dateCreated);

        }
    }




    //EFFECT: calculate the donation impact factor of the donor based on the conservation status of the animals
    //in his/her list of animals
    public void donationImpactCalculator() {

    }

}
