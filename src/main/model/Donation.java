package model;

import java.time.LocalDateTime;

public class Donation {
    private Animal animal;
    private Donor donor;
    private double amount;
    private LocalDateTime dateTimeDonationCreated;


    public Donation(Animal animal, Donor donor, double amount, LocalDateTime dateTimeDonationCreated) {
        this.animal = animal;
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

    public Animal getAnimal(){
        return animal;
    }

    public LocalDateTime getDateTimeDonationCreated() {
        return dateTimeDonationCreated;
    }



}
