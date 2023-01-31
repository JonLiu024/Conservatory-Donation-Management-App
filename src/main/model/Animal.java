package model;

public class Animal {

    public static final String EW = "Extinct in the Wild";
    public static final String CR = "Critically Endangered";
    public static final String EN = "Endangered";
    public static final String VU = "Vulnerable";
    public static final String NT = "Near Threatened";
    public static final String CD = "Conservation Dependent";
    public static final String LC = "Least Concerned";

    private String nickname;
    private String speciesName;
    private String conservationStatus;

    private double targetFunding;
    private double amountFunded;
    private double expectedShelteredTimeInMonths;
    private Story story;

    public Animal(String nickname, String speciesName, String conservationStatus, double targetFunding,
                  double amountFunded, double expectedShelteredTimeInMonths, Story story) {
        this.nickname = nickname;
        this.speciesName = speciesName;
        this.conservationStatus = conservationStatus;
        this.targetFunding = targetFunding;
        this.amountFunded = 0;
        this.expectedShelteredTimeInMonths = expectedShelteredTimeInMonths;


    }


    public void raiseFund(double amount){
        amountFunded += amount;
    }


    //getters
    public double getTargetFunding() {
        return this.targetFunding;
    }

    public double getAmountFunded() {
        return this.amountFunded;
    }

    public String getSpeciesName(){
        return this.speciesName;
    }

    public String getConservationStatus(){
        return this.conservationStatus;
    }






}
