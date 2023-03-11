package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TestWildlife {
    private Wildlife wildlife1;
    private Wildlife wildlife2;
    private Donor donor1;
    private Donor donor2;
    private Donation donation1;
    private Donation donation2;
    private Donation donation3;
    private String description;



    @BeforeEach
    public void setup() {
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife2 = new Wildlife("dog", 1000, ConservationStatus.EN, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donor2 = new Donor("234", "great@ubc.ca");
        donation1 = new Donation(wildlife1.getWildlifeID(), 100);
        donation2 = new Donation(wildlife2.getWildlifeID(), 20);
        donation3 = new Donation(wildlife1.getWildlifeID(), 20);
        description = "This snow leopard is injured!";

    }


    @Test
    public void testConstructor() {
        assertEquals("cat", wildlife1.getSpeciesName());
        assertEquals(2000, wildlife1.getTargetFunding());
        assertEquals(ConservationStatus.CD, wildlife1.getConservationStatus());
        assertEquals(0, wildlife1.getListOfDonors().size());
        assertEquals(0, wildlife1.getDonationRecords().size());
        assertFalse(wildlife1.getIsFullyFunded());
        assertEquals(LocalDate.now(), wildlife1.getAdmissionDate());
        assertNotNull(wildlife1.getWildlifeID());
        assertNull(wildlife1.getDateFullyFunded());
    }

    @Test
    public void testSetDescription() {
        wildlife1.setDescription(description);
        assertEquals(description, wildlife1.getDescription());
        assertEquals("This snow leopard is injured!", wildlife1.getDescription());

    }

    @Test
    public void testWildlifeIDGenerator() {
        assertEquals(6, wildlife1.wildlifeIDGenerator().length());
    }

    @Test
    public void testAddDonorToListNonDuplicate() {
        wildlife1.addDonorToList(donor1);
        wildlife1.addDonorToList(donor2);
        assertEquals(2, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));
        assertTrue(wildlife1.getListOfDonors().contains(donor2));

    }

    @Test
    public void testAddDonorToListDuplicate() {
        wildlife1.addDonorToList(donor1);
        wildlife1.addDonorToList(donor1);
        assertEquals(1, wildlife1.getListOfDonors().size());
        assertTrue(wildlife1.getListOfDonors().contains(donor1));

    }


    @Test
    public void testAddDonationToRecordsDuplicate() {
        wildlife1.addDonationToRecords(donation1);
        wildlife1.addDonationToRecords(donation1);
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertTrue(wildlife1.getDonationRecords().contains(donation1));

    }


    @Test
    public void testAddDonationToRecordsNonDuplicate() {
        wildlife1.addDonationToRecords(donation1);
        wildlife1.addDonationToRecords(donation3);
        assertEquals(2, wildlife1.getDonationRecords().size());
        assertTrue(wildlife1.getDonationRecords().contains(donation1));
        assertTrue(wildlife1.getDonationRecords().contains(donation3));

    }

    @Test
    public void testAddDonationToRecordsDonationWithDifferentWildlife() {
        wildlife1.addDonationToRecords(donation1);
        wildlife1.addDonationToRecords(donation2);
        assertEquals(1, wildlife1.getDonationRecords().size());
        assertTrue(wildlife1.getDonationRecords().contains(donation1));
        assertFalse(wildlife1.getDonationRecords().contains(donation2));


    }

    @Test
    public void testRaisedFundLessThanTargetFunding() {
        assertEquals(10, wildlife1.raiseFund(10));
        assertEquals(10, wildlife1.getAmountFunded());
        assertFalse(wildlife1.getIsFullyFunded());

    }

    @Test
    public void testRaisedFundEqualsTargetFunding() {
        assertEquals(2000, wildlife1.raiseFund(2000));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertTrue(wildlife1.getIsFullyFunded());

    }


    @Test
    public void testRaisedFundLargerThanTargetFunding() {
        assertEquals(2000, wildlife1.raiseFund(2001));
        assertEquals(2000, wildlife1.getAmountFunded());
        assertTrue(wildlife1.getIsFullyFunded());

    }




}
