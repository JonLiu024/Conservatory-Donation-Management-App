package persistence;

import model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonTest {



    protected void checkWildlife(Wildlife expectedWildlife, Wildlife wildlife) {
        assertEquals(expectedWildlife.getSpeciesName(), wildlife.getSpeciesName());
        assertEquals(expectedWildlife.getWildlifeID(), wildlife.getWildlifeID());
        assertEquals(expectedWildlife.getConservationStatus(), wildlife.getConservationStatus());
        assertEquals(expectedWildlife.getDateFullyFunded(), wildlife.getDateFullyFunded());
        assertEquals(expectedWildlife.getIsFullyFunded(), wildlife.getIsFullyFunded());
        assertEquals(expectedWildlife.getAdmissionDate(), wildlife.getAdmissionDate());
        assertEquals(expectedWildlife.getTargetFunding(), wildlife.getTargetFunding());
        assertEquals(expectedWildlife.getAmountFunded(), wildlife.getAmountFunded());
        assertEquals(expectedWildlife.getDonationRecords().size(), wildlife.getDonationRecords().size());
        for (int i = 0; i < expectedWildlife.getDonationRecords().size(); i++) {
            checkDonations(expectedWildlife.getDonationRecords().get(i),wildlife.getDonationRecords().get(i));
        }
        assertEquals(expectedWildlife.getListOfDonors().size(), wildlife.getListOfDonors().size());
        for (int i = 0; i < expectedWildlife.getListOfDonors().size(); i++) {
            checkDonor(expectedWildlife.getListOfDonors().get(i), wildlife.getListOfDonors().get(i));
        }
        assertEquals(expectedWildlife.getDescription(), wildlife.getDescription());


    }


    protected void checkDonations(Donation expected, Donation observed) {
        assertEquals(expected.getWildlifeID(), observed.getWildlifeID());
        assertEquals(expected.getAmount(), observed.getAmount());
        assertEquals(expected.getDateDonationMade(), observed.getDateDonationMade());
    }


    protected void checkDonor(Donor expected, Donor observed) {
        assertEquals(expected.getDonorID(), observed.getDonorID());
        assertEquals(expected.getProfileCreationDate(), observed.getProfileCreationDate());
        assertEquals(expected.getTotalFundingDonated(), observed.getTotalFundingDonated());
        assertEquals(expected.getEmailAddress(), observed.getEmailAddress());
        assertEquals(expected.getRecordsOfDonations().size(), observed.getRecordsOfDonations().size());
        for (int i = 0; i < expected.getRecordsOfDonations().size(); i++) {
            checkDonations(expected.getRecordsOfDonations().get(i), observed.getRecordsOfDonations().get(i));
        }

    }

}
