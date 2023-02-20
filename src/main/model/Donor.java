package model;

import org.w3c.dom.CDATASection;
import Exception.*;
import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.List.*;
import java.time.*;

public class Donor {
    private String name;
    private LocalDateTime accountCreationDate;
    private double totalFundingDonated;
    private List<Animal> listOfAnimals;
    private double donationImpact;






    public Donor(String name) {
        this.name = name;
        this.totalFundingDonated = totalFundingDonated;
        listOfAnimals = new ArrayList<>();
        accountCreationDate = LocalDateTime.now();
        totalFundingDonated = 0;

    }


    public void makeDonation(Animal animal, double amount) throws InvalidAmountDonationException {
        if (amount <= 0) {
            throw new InvalidAmountDonationException("Please enter an valid selection");
        }
        addAnimalToList(animal);
        animal.raiseFund(amount);

    }

    public void addAnimalToList(Animal animal){
        if (!listOfAnimals.contains(animal)) {
            listOfAnimals.add(animal);
        }
    }




    //EFFECT: calculate the donation impact factor of the donor based on the conservation status of the animals
    //in his/her list of animals
    public void donationImpactCalculator() {

    }

}
