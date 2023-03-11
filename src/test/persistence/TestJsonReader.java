package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader extends JsonTest{


    @BeforeEach
    void setup() {
        try {
            ConservationSite cs1 = new ConservationSite();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyCsJsonFile.json");
            writer.open();
            writer.write(cs1);
            writer.close();

        } catch (IOException e) {
            fail("Exception should not be thrown!");
        }
    }

    @Test
    void testReaderNonExistingFile() {
        JsonReader jsonReader = new JsonReader("./data/NoSuchFile.json");
        try {
            ConservationSite cs = jsonReader.read();
            fail("IO exception expected");
        } catch (IOException e) {
            //pass
        }
    }



    @Test
    void testReaderEmptyFile() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyCsJsonFile.json");
        try {
            ConservationSite cs = jsonReader.read();
            assertEquals("Wildlife Conservation Facility", cs.getName());
            assertEquals(0, cs.getListOfWildlifeNotFullyFunded().size());
            assertEquals(0, cs.getWildlifeListFullyFunded().size());
            assertEquals(0, cs.getListOfDonors().size());
            assertEquals(0, cs.getTotalTargetFunding());
            assertEquals(0, cs.getTotalTargetFunding());
        } catch (IOException e) {
            fail("There is some problems with reading the files");
        }
    }

    @Test
    void testReaderGeneralConservationSite() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCsJsonFile.json");
        try{
            ConservationSite cs1 = new ConservationSite();
            Wildlife wildlife1 = new Wildlife("orca", 1000, ConservationStatus.CD, LocalDate.now());
            Wildlife wildlife2 = new Wildlife("blue whale", 400, ConservationStatus.EN, LocalDate.now());
            cs1.addWildlife(wildlife1);
            cs1.addWildlife(wildlife2);
            wildlife2.setAmountFunded(400);
            cs1.moveWildlifeToFullyFundedList(wildlife2);
            Donor donor1 = new Donor("Jon", "jon@ubc.ca");
            wildlife1.addDonorToList(donor1);
            wildlife1.raiseFund(300);
            Donation donation = new Donation(wildlife1.getWildlifeID(), 300);
            wildlife1.addDonationToRecords(donation);
            donor1.addDonationToList(donation);
            cs1.addDonor(donor1);
            cs1.setTotalFundingRaised(300);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralCsJsonFile.json");
            writer.open();
            writer.write(cs1);
            writer.close();


            ConservationSite cs2 = reader.read();
            assertEquals(cs1.getName(), cs2.getName());
            assertEquals(cs1.getTotalTargetFunding(), cs2.getTotalTargetFunding());
            assertEquals(cs1.getTotalFundingRaised(), cs2.getTotalFundingRaised());
            assertEquals(cs1.getWildlifeListNotFullyFunded().size(), cs2.getWildlifeListNotFullyFunded().size());
            checkWildlife(wildlife1, cs2.getWildlifeListNotFullyFunded().get(0));
            checkWildlife(wildlife2, cs2.getWildlifeListFullyFunded().get(0));
            checkDonor(donor1, cs2.getListOfDonors().get(0));
            checkDonations(donation, cs2.getListOfDonors().get(0).getRecordsOfDonations().get(0));

        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }
}


