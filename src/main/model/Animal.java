package model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Animal {

//    public static final String EW = "Extinct in the Wild";
//    public static final String CR = "Critically Endangered";
//    public static final String EN = "Endangered";
//    public static final String VU = "Vulnerable";
//    public static final String NT = "Near Threatened";
//    public static final String CD = "Conservation Dependent";
//    public static final String LC = "Least Concerned";

    private String nickname;
    private String speciesName;
    private ConservationStatus conservationStatus;
    private TypeConservationSite conservationType;
    private LocalDateTime addedDate;
    private List<Donor> listOfDonors;
    private List<Donation> donationRecords;
    private LocalDateTime releasedDate;
    private boolean isFullyFunded;



    private double targetFunding;
    private double FundingMetDate;
    private double amountFunded;
    private double expectedShelteredTimeInMonths;
    private Story story;

    public Animal(String nickname, String speciesName, double targetFunding
            , double expectedShelteredTimeInMonths) {
        this.nickname = nickname;
        this.speciesName = speciesName;
        this.targetFunding = targetFunding;
        this.expectedShelteredTimeInMonths = expectedShelteredTimeInMonths;
        listOfDonors = new ArrayList<>();
        this.isFullyFunded = false;
        addedDate = LocalDateTime.now();



    }






    //getters
    public String getNickname(){
        return this.nickname;
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


    public String toString() {
        return nickname + " " + speciesName + " " + conservationStatus + " " + "\n"
        + targetFunding + " " + amountFunded + " " + expectedShelteredTimeInMonths;
    }

    //setters

    public void setNickname(String nickname) {this.nickname = nickname;}
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
        System.out.println(getNickname() + "has following donors:");
        for (Donor donor: listOfDonors) {
            System.out.println(donor.getName());
        }
    }



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
            ReleasedAnimals.addTolist(this);
            releasedDate = LocalDateTime.now();

        }

    }


}



