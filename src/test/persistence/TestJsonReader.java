package persistence;

import model.ConservationSite;
import model.ConservationStatus;
import model.Donor;
import model.Wildlife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

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
            Donor donor1 = new Donor("Jon", "jon@ubc.ca");
            cs1.addWildlife(wildlife1);
            cs1.addWildlife(wildlife2);
            cs1.addDonor(donor1);
            cs1.setTotalFundingRaised(1000);
            cs1.setTotalTargetFunding(1400);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCsJsonFile.json");
            writer.open();
            writer.write(cs1);
            writer.close();


            ConservationSite cs2 = reader.read();
            assertEquals(cs1.getName(), cs2.getName());
            assertEquals(cs1.getTotalTargetFunding(), cs2.getTotalTargetFunding());
            assertEquals(cs1.getTotalFundingRaised(), cs2.getTotalFundingRaised());
            assertEquals(cs1.getWildlifeListNotFullyFunded().size(), cs2.getWildlifeListNotFullyFunded().size());
            checkWildlife(wildlife1, cs2.getWildlifeListNotFullyFunded().get(0));
            checkWildlife(wildlife2, cs2.getWildlifeListNotFullyFunded().get(1));
            checkDonor(donor1, cs2.getListOfDonors().get(0));

        } catch (IOException e) {
            fail("IOException not expected!");
        }
    }
}


