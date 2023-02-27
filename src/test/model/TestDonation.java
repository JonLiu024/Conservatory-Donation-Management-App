package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestDonation {

    private ConservationSite conservationSite1;
    private Wildlife wildlife1;
    private Wildlife wildlife2;
    private Wildlife wildlife3;
    private Donor donor1;
    private Donor donor2;


    @BeforeEach
    public void setup() {
        conservationSite1 = new ConservationSite();
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife2 = new Wildlife("dog", 1000, ConservationStatus.EN, LocalDate.now());
        wildlife3 = new Wildlife("corba snake", 100, ConservationStatus.CD, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donor2 = new Donor("234", "great@ubc.ca");

    }


    @Test
    public void testConstructor(){
        Donation donation = new Donation(wildlife1, donor1, 2);

    }
}

