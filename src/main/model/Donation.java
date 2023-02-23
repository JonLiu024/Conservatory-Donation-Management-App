package model;

import java.time.LocalDateTime;

public class Donation {
    private Wildlife wildlife;
    private Donor donor;
    private double amount;
    private LocalDateTime dateTimeDonationCreated;


    public Donation(Wildlife wildlife, Donor donor, double amount, LocalDateTime dateTimeDonationCreated) {
        this.wildlife = wildlife;
        this.donor = donor;
        this.amount = amount;
        this.dateTimeDonationCreated = dateTimeDonationCreated;

    }

    //getters
    public Donor getDonor(){
        return donor;
    }

    public double getAmount(){
        return amount;
    }

    public Wildlife getAnimal(){
        return wildlife;
    }

    public LocalDateTime getDateTimeDonationCreated() {
        return dateTimeDonationCreated;
    }



}
