package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class ConservationSiteTest {

    private ConservationSite conservationSite1;
    private Wildlife wildlife1;
    private Wildlife wildlife2;

    private Donor donor1;
    private Donor donor2;


    @BeforeEach
    public void setup() {
        conservationSite1 = new ConservationSite();
        wildlife1 = new Wildlife("cat", 2000, ConservationStatus.CD, LocalDate.now());
        wildlife2 = new Wildlife("dog", 1000, ConservationStatus.EN, LocalDate.now());
        donor1 = new Donor("jon26", "jona@ubc.ca");
        donor2 = new Donor("234", "great@ubc.ca");

    }


    @Test
    public void testConstructor(){
        assertEquals(0, conservationSite1.getTotalFundingRaised());
        assertEquals(0, conservationSite1.getTotalTargetFunding());
        assertEquals(0, conservationSite1.getWildlifeListFullyFunded().size());
        assertEquals(0, conservationSite1.getListOfWildlifeNotFullyFunded().size());
        assertEquals(0, conservationSite1.getListOfDonors().size());
        assertEquals("Wildlife Conservation Facility", conservationSite1.getName());

    }

    @Test
    public void testAddWildlifeTwoDifferentWL() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);
        assertEquals(2, conservationSite1.getListOfAllWL().size());
        assertFalse(conservationSite1.getWildlifeListFullyFunded().contains(wildlife1));
        assertFalse(conservationSite1.getWildlifeListFullyFunded().contains(wildlife2));
        assertTrue(conservationSite1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
        assertTrue(conservationSite1.getListOfWildlifeNotFullyFunded().contains(wildlife2));

    }

    @Test
    public void testAddWildlifeDuplicateWL() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife1);
        assertEquals(1, conservationSite1.getListOfWildlifeNotFullyFunded().size());
        assertTrue(conservationSite1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
        assertFalse(conservationSite1.getListOfWildlifeNotFullyFunded().contains(wildlife2));
    }


    @Test
    public void testMoveWLToFullyFundedList() {
        conservationSite1.addWildlife(wildlife1);
        assertNull(wildlife1.getDateFullyFunded());
        assertFalse(conservationSite1.getWildlifeListFullyFunded().contains(wildlife1));

        conservationSite1.moveWildlifeToFullyFundedList(wildlife1);
        assertEquals(LocalDate.now(), wildlife1.getDateFullyFunded());
        assertTrue(conservationSite1.getWildlifeListFullyFunded().contains(wildlife1));
        assertFalse(conservationSite1.getListOfWildlifeNotFullyFunded().contains(wildlife1));
    }

    @Test
    public void testGetListOfAllWLBothListsNonEmpty() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);
        conservationSite1.moveWildlifeToFullyFundedList(wildlife2);

        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservationSite1.getListOfAllWL().size());

    }

    @Test
    public void testGetListOfAllWLFullyFundedListEmpty() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);

        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservationSite1.getListOfAllWL().size());

    }

    @Test
    public void testGetListOfAllWLNonFullyFundedListEmpty() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);
        conservationSite1.moveWildlifeToFullyFundedList(wildlife2);
        conservationSite1.moveWildlifeToFullyFundedList(wildlife1);

        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife1));
        assertTrue(conservationSite1.getListOfAllWL().contains(wildlife2));
        assertEquals(2, conservationSite1.getListOfAllWL().size());

    }


    @Test
    public void testAddDonor() {
        conservationSite1.addDonor(donor1);
        conservationSite1.addDonor(donor2);
        conservationSite1.addDonor(donor2);

        assertEquals(2, conservationSite1.getDonorIDList().size());
        assertTrue(conservationSite1.getListOfDonors().contains(donor1));
        assertTrue(conservationSite1.getListOfDonors().contains(donor2));

    }



    @Test
    public void testGetDonorIDList() {
        conservationSite1.addDonor(donor2);
        conservationSite1.addDonor(donor1);

        assertEquals(2, conservationSite1.getDonorIDList().size());
        assertTrue(conservationSite1.getDonorIDList().contains("jon26"));
        assertTrue(conservationSite1.getDonorIDList().contains("234"));

    }

    @Test
    public void testFundsRaisedCalculatorZero() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);
        assertEquals(0, conservationSite1.fundsRaisedCalculator());

    }

    @Test
    public void testFundsRaisedCalculatorLargerThanZero() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);
        wildlife1.raiseFund(100);
        wildlife2.raiseFund(200);

        assertEquals(wildlife1.getAmountFunded() + wildlife2.getAmountFunded(), conservationSite1.fundsRaisedCalculator());

    }

    @Test
    public void testTargetFundingCalculator() {
        conservationSite1.addWildlife(wildlife1);
        conservationSite1.addWildlife(wildlife2);

        assertEquals(wildlife1.getTargetFunding() + wildlife2.getTargetFunding(), conservationSite1.targetFundingCalculator());

    }


}
