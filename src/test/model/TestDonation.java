package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class TestDonation {

    private Wildlife wildlife1;
    private Donor donor1;



    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");


    }


    @Test
    public void testConstructor(){
        Donation donation = new Donation(wildlife1, donor1, 2);
        assertEquals(2, donation.getAmount());
        assertEquals(wildlife1, donation.getWildlife());
        assertEquals(donor1, donation.getDonor());
        assertEquals(LocalDate.now(), donation.getDateDonationMade());

    }
}

