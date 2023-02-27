package model;


import java.time.LocalDate;

//Representing the donation a donor makes to a wildlife. a donation is associated with
// a recipient wildlife, a donor, an amount, and the date the donation is made
public class Donation {
    private Wildlife wildlife; //the wildlife receiving the donation
    private Donor donor;       //the donor of the donation
    private double amount;     //the amount of funds being donated
    private LocalDate dateDonationMade;  //the local date the donation is made

    //REQUIRES: non-null wildlife and donor objects, amount > 0
    //EFFECT: create a donation object and set wildlife and donor and amount,
    // set dateDonationMade to be the current local date
    public Donation(Wildlife wildlife, Donor donor, double amount) {
        this.wildlife = wildlife;
        this.donor = donor;
        this.amount = amount;
        this.dateDonationMade = LocalDate.now();

    }

    //getters
    public Donor getDonor() {

        return donor;
    }

    public double getAmount() {

        return amount;
    }

    public Wildlife getWildlife() {

        return wildlife;
    }

    public LocalDate getDateDonationMade() {

        return dateDonationMade;
    }
}


