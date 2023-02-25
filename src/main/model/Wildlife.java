package model;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wildlife {

    private static final String EW = "Extinct in the Wild";
    private static final String CR = "Critically Endangered";
    private static final String EN = "Endangered";
    private static final String VU = "Vulnerable";
    private static final String NT = "Near Threatened";
    private static final String CD = "Conservation Dependent";
    private static final String LC = "Least Concerned";


    private static final String GL = "Grasslands";
    private static final String FS = "Forests";
    private static final String DS = "Desserts";
    private static final String MT = "Mountains";
    private static final String PR = "Polar Regions";
    private static final String AQ = "Aquatic Habitats";

    private String wildlifeID;
    private String speciesName;
    private ConservationStatus conservationStatus;
    private LocalDate admissionDate;
    private List<Donor> listOfDonors;
    private List<Donation> donationRecords;
    private LocalDate fundingMetDate;
    private boolean isFullyFunded;



    private double targetFunding;
    private double amountFunded;
    private Description description;

    public Wildlife(String speciesName, double targetFunding,
                    ConservationStatus conservationStatus, LocalDate admissionDate) {
        this.speciesName = speciesName;
        this.targetFunding = targetFunding;
        this.conservationStatus = conservationStatus;
        donationRecords = new ArrayList<>();
        listOfDonors = new ArrayList<>();
        this.isFullyFunded = false;
        this.admissionDate = admissionDate;
        this.wildlifeID = wildlifeIDgenerator();

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

    public LocalDate getFundingMetDate() {
        return fundingMetDate;
    }

    public boolean isFullyFunded() {

        return isFullyFunded;
    }

    public List<Donor> getListOfDonors() {
        return listOfDonors;
    }

    public List<Donation> getDonationRecords() {
        return donationRecords;
    }

    public Description getDescription() {

        return description;
    }


//    public String toString() {
//        return wildlifeID + " " + speciesName + " " + conservationStatus.toString() + " " + "\n"
//        + conservationType.toString() + targetFunding + " " + amountFunded + " " +;
//    }

    //setters
    public void setWildlifeID(String wildlifeID) {
        this.wildlifeID = wildlifeID;
    }

    public void setConservationStatus(ConservationStatus cs) {
        this.conservationStatus = cs;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public void setTargetFunding(double targetFunding) {
        this.targetFunding = targetFunding;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setFundingMetDate(LocalDate fundingMetDate) {
        this.fundingMetDate = fundingMetDate;
    }



    public String wildlifeIDgenerator() {
        Random r = new Random();
        String fourDigitsCode = "";
        for (int i = 0; i < 4; i++) {
            fourDigitsCode = fourDigitsCode + r.nextInt(10);
        }
        return conservationStatus.toString() + fourDigitsCode;
    }







    public void raiseFund(double amount) {
        amountFunded += amount;
//        if (amountFunded >= targetFunding) {
//            isFullyFunded = true;
//            double excessFundAmount = amountFunded - targetFunding;
//        }
    }

    public void addDonorToTheList(Donor donor) {
        if (!listOfDonors.contains(donor)) {
            listOfDonors.add(donor);
        }
    }

//    public void fullyFunded(){
//        if (targetFunding >= amountFunded){
//
//
//            isFullyFunded = true;
////            .addTolist(this);
//            releasedDate = LocalDateTime.now();
//
//        }

}






