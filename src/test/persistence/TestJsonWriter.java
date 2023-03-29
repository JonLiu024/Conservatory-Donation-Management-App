package persistence;

import model.Conservatory;
import model.ConservationStatus;
import model.Donor;
import model.Wildlife;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonWriter extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/invalidAddress\0:fileName.json");
            writer.open();
            fail("The file should not open, IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }


    @Test
    void testWriterEmptyCsValidFile() {
        try{
            Conservatory cs = new Conservatory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCsJsonFile.json");
            writer.open();
            writer.write(cs);
            writer.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterEmptyCsJsonFile.json");
            Conservatory cs1 = jsonReader.read();
            assertEquals(cs.getName(), cs1.getName());
            assertEquals(0, cs1.getListOfDonors().size());
            assertEquals(0, cs1.getListOfWildlifeNotFullyFunded().size());
            assertEquals(0, cs1.getWildlifeListFullyFunded().size());
            assertEquals(cs.getTotalFundingRaised(), cs1.getTotalTargetFunding());
            assertEquals(cs.getTotalFundingRaised(), cs1.getTotalFundingRaised());

        } catch (IOException e) {
            fail("Exception is not expected!");
        }
    }

    @Test
    void testWriterGeneralCsValidFile() {
        try{
            Conservatory cs = new Conservatory();
            Wildlife wildlife1 = new Wildlife("orca", 1000, ConservationStatus.CD, LocalDate.now());
            Wildlife wildlife2 = new Wildlife("blue whale", 400, ConservationStatus.EN, LocalDate.now());
            Donor donor1 = new Donor("Jon", "jon@ubc.ca");
            cs.addWildlife(wildlife1);
            cs.addWildlife(wildlife2);
            cs.addDonor(donor1);
            cs.setTotalFundingRaised(1000);
            cs.setTotalTargetFunding(2000);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCsJsonFile.json");
            writer.open();
            writer.write(cs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCsJsonFile.json");
            Conservatory cs1 = reader.read();
            assertEquals(cs.getName(), cs1.getName());
            assertEquals(cs.getTotalTargetFunding(), cs1.getTotalTargetFunding());
            assertEquals(cs.getTotalFundingRaised(), cs1.getTotalFundingRaised());
            assertEquals(cs.getWildlifeListNotFullyFunded().size(), cs1.getWildlifeListNotFullyFunded().size());
            checkWildlife(wildlife1, cs1.getWildlifeListNotFullyFunded().get(0));
            checkWildlife(wildlife2, cs1.getWildlifeListNotFullyFunded().get(1));
            checkDonor(donor1, cs1.getListOfDonors().get(0));

        } catch (FileNotFoundException e) {
            fail("Exception is not expected!");
        } catch (IOException e) {
            fail("IOException not expected!");
        }


    }





}
