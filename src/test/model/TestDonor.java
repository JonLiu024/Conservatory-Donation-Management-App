package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestDonor {
    private Wildlife wildlife1;
    private Wildlife wildlife2;
    private Donor donor1;
    private Donor donor2;
    private Donation donation1;
    private Donation donation2;



    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife2 = new Wildlife("dog", 1000, ConservationStatus.EN, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donor2 = new Donor("234", "great@ubc.ca");
        donation1 = new Donation(wildlife1, donor1, 100);
        donation2 = new Donation(wildlife1, donor2, 20);

    }


    @Test
    public void testConstructor(){
        assertEquals("jon26", donor1.getDonorID());
        assertEquals("jona@ubc.ca", donor1.getEmailAddress());
        assertEquals(0, donor1.getListOfWildlife().size());
        assertEquals(0, donor1.getRecordsOfDonations().size());
        assertEquals(LocalDate.now(), donor1.getProfileCreationDate());
        assertEquals(0, donor1.getTotalFundingDonated());

    }

    @Test
    public void testAddWLToNoDuplicateList(){
        donor1.addWLToList(wildlife1);
        donor1.addWLToList(wildlife2);
        assertEquals(2, donor1.getListOfWildlife().size());
        assertTrue(donor1.getListOfWildlife().contains(wildlife1));
        assertTrue(donor1.getListOfWildlife().contains(wildlife2));

    }

    @Test
    public void testAddWLToDuplicateList(){
        donor1.addWLToList(wildlife1);
        donor1.addWLToList(wildlife1);
        assertEquals(1, donor1.getListOfWildlife().size());
        assertTrue(donor1.getListOfWildlife().contains(wildlife1));

    }

    @Test
    public void testAddDonationToList() {
        donor1.addDonationToList(donation1);
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertTrue(donor1.getRecordsOfDonations().contains(donation1));

    }

    @Test
    public void testAddDonationToListDonationHasDifferentDonor() {
        donor1.addDonationToList(donation2);
        assertEquals(0, donor1.getRecordsOfDonations().size());
        assertFalse(donor1.getRecordsOfDonations().contains(donation2));

    }

    @Test
    public void testMakeDonationLessThanTargetFunding() {
        assertEquals(100,donor1.makeDonation(wildlife1,100));
        assertEquals(100, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1, donor1.getRecordsOfDonations().get(0).getWildlife());
        assertEquals(donor1, donor1.getRecordsOfDonations().get(0).getDonor());
        assertEquals(100, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(1, donor1.getListOfWildlife().size());
        assertTrue(donor1.getListOfWildlife().contains(wildlife1));
        assertEquals(100, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertFalse(wildlife1.isFullyFunded());

    }

    @Test
    public void testMakeDonationMoreThanTargetFunding() {
        assertEquals(2000,donor1.makeDonation(wildlife1,2001));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1, donor1.getRecordsOfDonations().get(0).getWildlife());
        assertEquals(donor1, donor1.getRecordsOfDonations().get(0).getDonor());
        assertEquals(2000, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(1, donor1.getListOfWildlife().size());
        assertTrue(donor1.getListOfWildlife().contains(wildlife1));
        assertEquals(2000, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertTrue(wildlife1.isFullyFunded());

    }

    @Test
    public void testMakeDonationEqualsTargetFunding() {
        assertEquals(2000,donor1.makeDonation(wildlife1,2000));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertEquals(1, donor1.getRecordsOfDonations().size());
        assertEquals(wildlife1, donor1.getRecordsOfDonations().get(0).getWildlife());
        assertEquals(donor1, donor1.getRecordsOfDonations().get(0).getDonor());
        assertEquals(2000, donor1.getRecordsOfDonations().get(0).getAmount());
        assertEquals(1, donor1.getListOfWildlife().size());
        assertTrue(donor1.getListOfWildlife().contains(wildlife1));
        assertEquals(2000, donor1.getTotalFundingDonated());
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertTrue(wildlife1.isFullyFunded());

    }
}
