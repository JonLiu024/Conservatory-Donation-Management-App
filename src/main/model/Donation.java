package model;


import org.json.JSONObject;
import persistence.Writable;
import formatters.DateFormatter;

import java.time.LocalDate;

//Representing the donation a donor makes to a wildlife. a donation is associated with
// a recipient wildlife, a donor, an amount, and the date the donation is made
public class Donation implements Writable {
    private String wildlifeID; //the wildlife receiving the donation
    private double amount;     //the amount of funds being donated
    private LocalDate dateDonationMade;  //the local date the donation is made

    //REQUIRES: non-null wildlife and donor objects, amount > 0
    //EFFECT: create a donation object and set wildlife and donor and amount,
    // set dateDonationMade to be the current local date
    public Donation(String wildlifeID, double amount) {
        this.wildlifeID = wildlifeID;
        this.amount = amount;
        this.dateDonationMade = LocalDate.now();

    }

    //getters

    public double getAmount() {

        return amount;
    }

    public String getWildlifeID() {

        return wildlifeID;
    }

    public LocalDate getDateDonationMade() {

        return dateDonationMade;
    }


    //Setters

    public void setDateDonationMade(LocalDate dateDonationMade) {
        this.dateDonationMade = dateDonationMade;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wildlifeID", wildlifeID);
        jsonObject.put("amount", amount);
        jsonObject.put("dateDonationMade", DateFormatter.toStringLocalDate(dateDonationMade));
        return jsonObject;
    }

}


