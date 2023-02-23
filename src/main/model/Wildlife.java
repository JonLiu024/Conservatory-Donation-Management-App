package model;



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
    private TypeConservationSite conservationType;
    private LocalDateTime addedDate;
    private List<Donor> listOfDonors;
    private List<Donation> donationRecords;
    private LocalDateTime releasedDate;
    private boolean isFullyFunded;



    private double targetFunding;
    private double amountFunded;
    private double expectedShelteredTimeInMonths;
    private Story story;

    public Wildlife(String speciesName, double targetFunding
            , double expectedShelteredTimeInMonths, ConservationStatus conservationStatus, TypeConservationSite conservationType) {
        this.speciesName = speciesName;
        this.targetFunding = targetFunding;
        this.expectedShelteredTimeInMonths = expectedShelteredTimeInMonths;
        listOfDonors = new ArrayList<>();
        this.isFullyFunded = false;
        addedDate = LocalDateTime.now();
        this.wildlifeID = wildlifeIDgenerator();

    }






    //getters
    public String getWildlifeID(){
        return this.wildlifeID;
    }
    public double getExpectedShelteredTimeInMonths() {return this.expectedShelteredTimeInMonths;}
    public double getTargetFunding() {return this.targetFunding;}
    public double getAmountFunded() {return this.amountFunded;}
    public String getSpeciesName(){
        return this.speciesName;
    }
    public ConservationStatus getConservationStatus(){return this.conservationStatus;}
    public TypeConservationSite getConservationType() {return this.conservationType;}

    public LocalDateTime getAddedDate() {
        return addedDate;
    }
    public LocalDateTime getReleasedDate() {
        return releasedDate;
    }
    public boolean isFullyFunded() {
        return isFullyFunded;
    }
    public List<Donor> getListOfDonors() {return listOfDonors;}
    public List<Donation> getDonationRecords() {return donationRecords;}


    public String toString() {
        return wildlifeID + " " + speciesName + " " + conservationStatus + " " + "\n"
        + targetFunding + " " + amountFunded + " " + expectedShelteredTimeInMonths;
    }

    //setters
    public void setWildlifeID(String wildlifeID) {this.wildlifeID = wildlifeID;}
    public void setConservationStatus(ConservationStatus cs) {this.conservationStatus = cs;}
    public void setSpeciesName(String speciesName) {this.speciesName = speciesName;}
    public void setConservationType(TypeConservationSite conservationType) {
        this.conservationType = conservationType;
    }
    public void setExpectedShelteredTimeInMonths(double expectedShelteredTimeInMonths) {
        this.expectedShelteredTimeInMonths = expectedShelteredTimeInMonths;
    }
    public void setTargetFunding(double targetFunding) {this.targetFunding = targetFunding;}
    public void setStory(Story story) {this.story = story;}



    public void displayDonors() {
        System.out.println(getWildlifeID() + "has following donors:");
        for (Donor donor: listOfDonors) {
            System.out.println(donor.getDonorID());
        }
    }

    public String wildlifeIDgenerator() {
        Random r = new Random();
        String threeDigitCode = "";
        for (int i = 0; i < 3; i++) {
            threeDigitCode = threeDigitCode + r.nextInt(10);
        }
        return conservationType.toString() + conservationStatus.toString() + threeDigitCode;
        }
        ;






    public void raiseFund(double amount){
        amountFunded += amount;
    }

    public void addDonorToTheList(Donor donor) {
        if (!listOfDonors.contains(donor)) {
            listOfDonors.add(donor);
        }
    }

    public void fullyFunded(){
        if (targetFunding >= amountFunded){

            double excessFundAmount = amountFunded - targetFunding;
            isFullyFunded = true;
//            .addTolist(this);
            releasedDate = LocalDateTime.now();

        }

    }


}



