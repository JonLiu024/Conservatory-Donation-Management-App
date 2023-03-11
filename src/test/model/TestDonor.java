package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestDonor {
    private Wildlife wildlife1;
    private Donor donor1;
    private Donation donation1;



    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donation1 = new Donation(wildlife1.getWildlifeID(), 100);

    }


    @Test
    public void testConstructor(){
        assertEquals("jon26", donor1.getDonorID());
        assertEquals("jona@ubc.ca", donor1.getEmailAddress());
        assertEquals(0, donor1.getRecordsOfDonations().size());
        assertEquals(LocalDate.now(), donor1.getProfileCreationDate());
        assertEquals(0, donor1.getTotalFundingDonated());

    }


    @Test
    public void testAddDonationToList() {
        donor1.addDonationToList(donation1);
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertTrue(donor1.getRecordsOfDonations().contains(donation1));

    }


    @Test
    public void testMakeDonationLessThanTargetFunding() {
        assertEquals(100,donor1.makeDonation(wildlife1,100));
        assertEquals(100, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1.getWildlifeID(), donor1.getRecordsOfDonations().get(0).getWildlifeID());
        assertEquals(100, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(100, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertFalse(wildlife1.getIsFullyFunded());

    }

    @Test
    public void testMakeDonationMoreThanTargetFunding() {
        assertEquals(2000,donor1.makeDonation(wildlife1,2001));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1.getWildlifeID(), donor1.getRecordsOfDonations().get(0).getWildlifeID());
        assertEquals(2000, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(2000, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertTrue(wildlife1.getIsFullyFunded());

    }

    @Test
    public void testMakeDonationEqualsTargetFunding() {
        assertEquals(2000,donor1.makeDonation(wildlife1,2000));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1.getWildlifeID(), donor1.getRecordsOfDonations().get(0).getWildlifeID());
        assertEquals(2000, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(2000, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertTrue(wildlife1.getIsFullyFunded());

    }
}
