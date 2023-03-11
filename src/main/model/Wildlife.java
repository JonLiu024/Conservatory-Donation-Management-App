package model;


import model.formatters.DateFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//Representing a rescued wildlife in the conservation facility; it has an assigned wildlifeID,
//a species name, conservation status, an admission date, target funding, and currently raised funding,
//and a short description
public class Wildlife implements Writable {


    private String wildlifeID;      //wildlife's ID in the conservation site
    private String speciesName;     //wildlife's species
    private ConservationStatus conservationStatus;   //wildlife's conservation status
    private LocalDate admissionDate;     //date the wildlife is admitted into the conservation site
    private List<Donor> listOfDonors;     //list of donor that makes donations to this wildlife
    private List<Donation> donationRecords;    // list of donation records
    private LocalDate dateFullyFunded;     //date the wildlife is fully funded (is null if it has not been fully funded)
    private boolean isFullyFunded;      //if the wildlife is fully funded
    private double targetFunding;       //wildlife's target funding
    private double amountFunded;        //funds that has been raised for this wildlife
    private String description;    //a description associated with this animal


    //REQUIRES: speciesName is not null, targetFunding > 0, ConservationStatus is not null, admissionDate is not null
    //EFFECT: create a wildlife object; the speciesName is set as speciesName, targetFunding is
    //set as targetFunding, conservationStatus is set as conservationStatus,  admissionDate is
    //set as localDate object admissionDate, donationRecords is set as an empty array list of donations,
    //listOfDonors is set as an empty array list of donors, isFullyFunded is set false, create and set wildlifeID using
    //wildlifeIDGenerator()
    public Wildlife(String speciesName, double targetFunding,
                    ConservationStatus conservationStatus, LocalDate admissionDate) {
        setSpeciesName(speciesName);
        setTargetFunding(targetFunding);
        setConservationStatus(conservationStatus);
        donationRecords = new ArrayList<>();
        listOfDonors = new ArrayList<>();
        amountFunded = 0;
        this.isFullyFunded = false;
        setAdmissionDate(admissionDate);
        this.wildlifeID = wildlifeIDGenerator();
        this.description = "none";

    }






    //getters
    public String getWildlifeID() {

        return this.wildlifeID;
    }

    public double getTargetFunding() {

        return this.targetFunding;
    }

    public double getAmountFunded() {

        return this.amountFunded;
    }

    public String getSpeciesName() {

        return this.speciesName;
    }

    public ConservationStatus getConservationStatus() {

        return this.conservationStatus;
    }

    public LocalDate getAdmissionDate() {

        return admissionDate;
    }

    public LocalDate getDateFullyFunded() {

        return dateFullyFunded;
    }

    public boolean getIsFullyFunded() {

        return isFullyFunded;
    }

    public List<Donor> getListOfDonors() {

        return listOfDonors;
    }

    public List<Donation> getDonationRecords() {

        return donationRecords;
    }

    public String getDescription() {

        return description;
    }


    //setters

    public void setConservationStatus(ConservationStatus cs) {

        this.conservationStatus = cs;
    }

    public void setSpeciesName(String speciesName) {

        this.speciesName = speciesName;
    }

    public void setTargetFunding(double targetFunding) {

        this.targetFunding = targetFunding;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setAdmissionDate(LocalDate admissionDate) {

        this.admissionDate = admissionDate;
    }

    public void setDateFullyFunded(LocalDate dateFullyFunded) {

        this.dateFullyFunded = dateFullyFunded;
    }

    public void setWildlifeID(String wildlifeID) {
        this.wildlifeID = wildlifeID;
    }

    public void setIsFullyFunded(boolean fullyFunded) {
        isFullyFunded = fullyFunded;
    }

    public void setListOfDonors(List<Donor> listOfDonors) {
        this.listOfDonors = listOfDonors;
    }

    public void setAmountFunded(double amountFunded) {
        this.amountFunded = amountFunded;
    }

    public void setDonationRecords(List<Donation> donationRecords) {
        this.donationRecords = donationRecords;
    }



    //EFFECT: create and returns an ID string consisting of the wildlife's
    // conservationStatus (Acronym in two capital letters), followed by four random digits (0-9)
    public String wildlifeIDGenerator() {
        Random r = new Random();
        String fourDigitsCode = "";
        for (int i = 0; i < 4; i++) {
            fourDigitsCode = fourDigitsCode + r.nextInt(10);
        }
        return conservationStatus.toString() + fourDigitsCode;
    }



    //REQUIRES: donor is not null
    //MODIFIES: this
    //EFFECT: donor is added into the listOfDonors if it has not been previously added
    public void addDonorToList(Donor donor) {
        if (!listOfDonors.contains(donor)) {
            listOfDonors.add(donor);
        }
    }

    //REQUIRES: d is not null
    //MODIFIES: this
    //EFFECT: donation d is added into the donationRecords if it has not been previously added and if the
    //donation d is associated with this wildlife
    public void addDonationToRecords(Donation d) {
        if (!donationRecords.contains(d)) {
            if (d.getWildlifeID().equals(wildlifeID)) {
                donationRecords.add(d);
            }
        }
    }


    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECT: the amountFunded is increased by amount and the amount is returned if amountFunded is not
    //equal or larger than the targetFunding after the increment, else the amountFunded is increased to targetFunding,
    //isFullyFunded is set true, and the actual increment is returned
    public double raiseFund(double amount) {
        double amtToGetFullyFunded = this.targetFunding - this.amountFunded;
        if (amount + amountFunded >= targetFunding) {
            amountFunded += amtToGetFullyFunded;
            isFullyFunded = true;
            return amtToGetFullyFunded;
        } else {
            amountFunded += amount;
            return amount;
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("wildlifeID", wildlifeID);
        jsonobject.put("speciesName", speciesName);
        jsonobject.put("conservationStatus", conservationStatus);
        jsonobject.put("admissionDate", DateFormatter.toStringLocalDate(admissionDate));
        jsonobject.put("dateFullyFunded", DateFormatter.toStringLocalDate(dateFullyFunded));
        jsonobject.put("isFullyFunded", isFullyFunded);
        jsonobject.put("targetFunding", targetFunding);
        jsonobject.put("amountFunded", amountFunded);
        jsonobject.put("description", description);
        jsonobject.put("listOfDonors", listOfDonorsToJson());
        jsonobject.put("donationRecords", donationRecordsToJson());
        return jsonobject;
    }


    //EFFECT: returns donors of ths wildlife as a JSON array
    private JSONArray listOfDonorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Donor d: listOfDonors) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

    //EFFECT: returns donations made to this wildlife as a JSON array
    private JSONArray donationRecordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Donation d: donationRecords) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }


}






