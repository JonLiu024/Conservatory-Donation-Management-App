package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TestConservatory {

    private Conservatory conservatory1;
    private Wildlife wildlife1;
    private Wildlife wildlife2;

    private Donor donor1;
    private Donor donor2;
    private Donor donor3;
    private Donor donor4;


    @BeforeEach
    public void setup() {
        conservatory1 = new Conservatory();
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife2 = new Wildlife("dog", 1000, ConservationStatus.EN, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donor2 = new Donor("234", "great@ubc.ca");
        donor3 = new Donor("12", "12@ubc.ca");
        donor4 = new Donor("43", "iwt@ubc.ca");

    }


    @Test
    public void testConstructor(){
        assertEquals(0, conservatory1.getTotalFundingRaised());
        assertEquals(0, conservatory1.getTotalTargetFunding());
        assertEquals(0, conservatory1.getWildlifeListFullyFunded().size());
        assertEquals(0, conservatory1.getListOfWildlifeNotFullyFunded().size());
        assertEquals(0, conservatory1.getListOfDonors().size());
        assertEquals("Wildlife Conservation Facility", conservatory1.getName());

    }

    @Test
    public void testAddWildlifeTwoDifferentWL() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        assertEquals(2, conservatory1.getListOfAllWL().size());
        assertFalse(conservatory1.getWildlifeListFullyFunded().contains(wildlife1));
        assertFalse(conservatory1.getWildlifeListFullyFunded().contains(wildlife2));
        assertTrue(conservatory1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
        assertTrue(conservatory1.getListOfWildlifeNotFullyFunded().contains(wildlife2));

    }

    @Test
    public void testAddWildlifeDuplicateWL() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife1);
        assertEquals(1, conservatory1.getListOfWildlifeNotFullyFunded().size());
        assertTrue(conservatory1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
        assertFalse(conservatory1.getListOfWildlifeNotFullyFunded().contains(wildlife2));
    }


    @Test
    public void testMoveWLToFullyFundedList() {
        conservatory1.addWildlife(wildlife1);
        assertNull(wildlife1.getDateFullyFunded());
        assertFalse(conservatory1.getWildlifeListFullyFunded().contains(wildlife1));

        conservatory1.moveWildlifeToFullyFundedList(wildlife1);
        assertEquals(LocalDate.now(), wildlife1.getDateFullyFunded());
        assertTrue(conservatory1.getWildlifeListFullyFunded().contains(wildlife1));
        assertFalse(conservatory1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
    }

    @Test
    public void testGetListOfAllWLBothListsNonEmpty() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        conservatory1.moveWildlifeToFullyFundedList(wildlife2);

        assertTrue(conservatory1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservatory1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservatory1.getListOfAllWL().size());

    }

    @Test
    public void testGetListOfAllWLFullyFundedListEmpty() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);

        assertTrue(conservatory1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservatory1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservatory1.getListOfAllWL().size());

    }

    @Test
    public void testGetListOfAllWLNonFullyFundedListEmpty() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        conservatory1.moveWildlifeToFullyFundedList(wildlife2);
        conservatory1.moveWildlifeToFullyFundedList(wildlife1);

        assertTrue(conservatory1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservatory1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservatory1.getListOfAllWL().size());

    }


    @Test
    public void testAddSameDonorTwice() {
        conservatory1.addDonor(donor1);
        conservatory1.addDonor(donor2);
        conservatory1.addDonor(donor2);

        assertEquals(2, conservatory1.getDonorIDList().size());
        assertTrue(conservatory1.getListOfDonors().contains(donor1));
        assertTrue(conservatory1.getListOfDonors().contains(donor2));
    }


    @Test
    public void testUpdateRaisedFunding() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        wildlife1.setAmountFunded(300);
        wildlife2.setAmountFunded(200);
        conservatory1.updateRaisedFunding();
        assertEquals(500, conservatory1.getTotalFundingRaised());
    }



    @Test
    public void testGetDonorIDList() {
        conservatory1.addDonor(donor2);
        conservatory1.addDonor(donor1);
        assertEquals(2, conservatory1.getDonorIDList().size());
        assertTrue(conservatory1.getDonorIDList().contains("jon26"));
        assertTrue(conservatory1.getDonorIDList().contains("234"));
    }


    @Test
    public void testFundsRaisedCalculatorZero() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        assertEquals(0, conservatory1.fundsRaisedCalculator());
    }


    @Test
    public void testFundsRaisedCalculator() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        wildlife2.setAmountFunded(200);
        assertEquals(200, conservatory1.fundsRaisedCalculator());
    }


    @Test
    public void testMostGenerousDonorSingleDonor() {
        donor1.setTotalFundingDonated(98);
        donor2.setTotalFundingDonated(99);
        donor3.setTotalFundingDonated(100);
        conservatory1.addDonor(donor1);
        conservatory1.addDonor(donor2);
        conservatory1.addDonor(donor3);
        assertEquals(1, conservatory1.mostGenerousDonor().size());
        assertTrue(conservatory1.mostGenerousDonor().contains(donor3));
    }


    @Test
    public void testMostGenerousMultipleDonors() {
        donor1.setTotalFundingDonated(98);
        donor2.setTotalFundingDonated(100);
        donor3.setTotalFundingDonated(100);
        conservatory1.addDonor(donor1);
        conservatory1.addDonor(donor2);
        conservatory1.addDonor(donor3);
        assertEquals(2, conservatory1.mostGenerousDonor().size());
        assertTrue(conservatory1.mostGenerousDonor().contains(donor2));
        assertTrue(conservatory1.mostGenerousDonor().contains(donor3));
    }

    @Test
    public void testMostGenerousDonorMultipleDonors() {
        donor1.setTotalFundingDonated(98);
        donor2.setTotalFundingDonated(100);
        donor3.setTotalFundingDonated(100);
        donor4.setTotalFundingDonated(1002);
        conservatory1.addDonor(donor1);
        conservatory1.addDonor(donor2);
        conservatory1.addDonor(donor3);
        conservatory1.addDonor(donor4);

        assertEquals(1, conservatory1.mostGenerousDonor().size());
        assertTrue(conservatory1.mostGenerousDonor().contains(donor4));
    }


    @Test
    public void testFundsRaisedCalculatorLargerThanZero() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);
        wildlife1.raiseFund(100);
        wildlife2.raiseFund(200);
        assertEquals(wildlife1.getAmountFunded() + wildlife2.getAmountFunded(), conservatory1.fundsRaisedCalculator());

    }

    @Test
    public void testTargetFundingCalculator() {
        conservatory1.addWildlife(wildlife1);
        conservatory1.addWildlife(wildlife2);

        assertEquals(wildlife1.getTargetFunding() + wildlife2.getTargetFunding(), conservatory1.targetFundingCalculator());

    }


}
