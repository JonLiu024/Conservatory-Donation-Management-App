package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class TestDonation {

    private Wildlife wildlife1;



    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());

    }


    @Test
    public void testConstructor(){
        Donation donation = new Donation(wildlife1.getWildlifeID(), 2);
        assertEquals(2, donation.getAmount());
        assertEquals(wildlife1.getWildlifeID(), donation.getWildlifeID());
        assertEquals(LocalDate.now(), donation.getDateDonationMade());

    }
}

