package model;

import org.w3c.dom.CDATASection;
import Exception.*;
import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.List.*;
import java.time.*;
import formatters.DateTimeFormatter.*;

public class Donor {
    private String name;
    private LocalDateTime accountCreationDate;
    private double totalFundingDonated;
    private List<Animal> listOfAnimals;
    private double donationImpact;
    private List<Donation> recordsOfDonations;





    public Donor(String name) {
        this.name = name;
        this.totalFundingDonated = totalFundingDonated;
        listOfAnimals = new ArrayList<>();
        recordsOfDonations = new ArrayList<>();
        accountCreationDate = LocalDateTime.now();
        totalFundingDonated = 0;

    }


    //getters
    public String getName() {
        return this.name;
    }
    public LocalDateTime getAccountCreationDate(){
        return this.accountCreationDate;
    }
    public double getTotalFundingDonated() {
        return this.totalFundingDonated;
    }

    public List<Donation> getRecordsOfDonations() {
        return this.recordsOfDonations;
    }

    public double getDonationImpact(){
        return donationImpact;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }


    public void makeDonation(Animal animal, double amount) throws InvalidAmountDonationException {
        if (amount <= 0) {
            throw new InvalidAmountDonationException("Please enter a valid selection");
        }
        addAnimalToList(animal);
        animal.raiseFund(amount);
        totalFundingDonated += amount;
        recordsOfDonations.add(new Donation(animal, this, amount, LocalDateTime.now()));
        animal.addDonorToTheList(this);

    }

    public void addAnimalToList(Animal animal){
        if (!listOfAnimals.contains(animal)) {
            listOfAnimals.add(animal);
        }
    }

    public void printAllDonationsRecords() {
        for (Donation donation: recordsOfDonations) {
            String dateCreated = formatters.DateTimeFormatter.toStringLocalDateTime(donation.getDateTimeDonationCreated());
            System.out.println(donation.getDonor().getName() +" donated " + donation.getAmount() + " to " +
                    donation.getAnimal().getNickname() + " on " + dateCreated);

        }
    }




    //EFFECT: calculate the donation impact factor of the donor based on the conservation status of the animals
    //in his/her list of animals
    public void donationImpactCalculator() {

    }

}
