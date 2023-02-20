package model;

import OutInTheWild;

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
    private boolean releaseToTheWild;
    private boolean fullyFunded;


    private double targetFunding;
    private double FundingMetDate;
    private double amountFunded;
    private double expectedShelteredTimeInMonths;
    private Story story;

    public Animal(String nickname, String speciesName, double targetFunding
            , double expectedShelteredTimeInMonths, Story story) {
        this.nickname = nickname;
        this.speciesName = speciesName;
        this.targetFunding = targetFunding;
        this.expectedShelteredTimeInMonths = expectedShelteredTimeInMonths;
        listOfDonors = new ArrayList<>();
        this.releaseToTheWild = false;
        this.fullyFunded = true;
        addedDate = LocalDateTime.now();



    }
    public void a(){}


    public void raiseFund(double amount){
        amountFunded += amount;
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

    public void FullyFunded(){
        if (targetFunding >= amountFunded){

            double excessFundAmount = amountFunded - targetFunding;
            ;
        }
        fullyFunded = true;
        OutInTheWild.a
    }
}



