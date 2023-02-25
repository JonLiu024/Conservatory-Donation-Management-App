package model;

import java.time.LocalDateTime;

public class Donation {
    private Wildlife wildlife;
    private Donor donor;
    private double amount;
    private LocalDateTime dateTimeDonationMade;


    public Donation(Wildlife wildlife, Donor donor, double amount) {
        this.wildlife = wildlife;
        this.donor = donor;
        this.amount = amount;
        this.dateTimeDonationMade = LocalDateTime.now();

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

    public LocalDateTime getDateTimeDonationMade() {
        return dateTimeDonationMade;
    }

    //setters
    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setWildlife(Wildlife wildlife) {
        this.wildlife = wildlife;
    }

    public void setDateTimeDonationMade(LocalDateTime dateTime) {
        this.dateTimeDonationMade = dateTimeDonationMade;
    }
}
