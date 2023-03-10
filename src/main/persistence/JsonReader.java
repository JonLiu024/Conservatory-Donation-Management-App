package persistence;


import formatters.DateFormatter;
import model.ConservationSite;
import model.ConservationStatus;
import model.Description;
import model.Wildlife;
import model.Donor;
import model.Donation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//Representing a reader that reads Conservation site from JSON data stored in file
public class JsonReader {
    private String source; //source file pathname


    //REQUIRES: source is a valid pathname
    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    //EFFECTS: reads a conservationSite from file and returns it;
    // throws IOException if an error occurs reading data from file
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    public ConservationSite read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseConservationSite(jsonObject);

    }

    //EFFECTS: parses conservationSite from JSON object and returns it
    public ConservationSite parseConservationSite(JSONObject jsonObject) {
        ConservationSite cs = new ConservationSite();
        implementsWildlifeList(cs, jsonObject);
        implementsDonorList(cs, jsonObject);
        cs.setTotalTargetFunding(jsonObject.getDouble("totalTargetFunding"));
        cs.setTotalFundingRaised(jsonObject.getDouble("totalFundingRaised"));
        return cs;
    }

    //REQUIRES: cs is not null;
    //MODIFIES: cs
    //EFFECTS: parses wildlifeListNotFullyFunded and wildlifeListFullyFunded from JSON object
    //and added them into the conservation site cs
    private void implementsWildlifeList(ConservationSite cs, JSONObject jsonObject) {
        JSONArray jsonArrayWlNotFullyFunded = jsonObject.getJSONArray("wildlifeListNotFullyFunded");
        List<Wildlife> wildlifeListNotFullyFunded = new ArrayList<>();
        for (Object json: jsonArrayWlNotFullyFunded) {
            JSONObject nextWildlife = (JSONObject) json;
            wildlifeListNotFullyFunded.add(jsonToWildlife(nextWildlife));
        }
        cs.setWildlifeListNotFullyFunded(wildlifeListNotFullyFunded);

        JSONArray jsonArrayWlFullyFunded = jsonObject.getJSONArray("wildlifeListFullyFunded");
        List<Wildlife> wildlifeListFullyFunded = new ArrayList<>();
        for (Object json: jsonArrayWlFullyFunded) {
            JSONObject nextWildlife = (JSONObject) json;
            wildlifeListFullyFunded.add(jsonToWildlife(nextWildlife));
        }
        cs.setWildlifeListFullyFunded(wildlifeListFullyFunded);
    }

    //REQUIRES: cs is not null
    //MODIFIES: cs
    //EFFECT: parses list of donors from the JSON object and adds that into the Conservation site cs
    private void implementsDonorList(ConservationSite cs, JSONObject jsonObject) {
        JSONArray donorsListJsonArray = jsonObject.getJSONArray("listOfDonor");
        List<Donor> listOfDonor = new ArrayList<>();
        for (Object json: donorsListJsonArray) {
            JSONObject nextDonor = (JSONObject) json;
            if (!listOfDonor.contains(jsonToDonor(nextDonor))) {
                listOfDonor.add(jsonToDonor(nextDonor));
            }
        }
        cs.setListOfDonors(listOfDonor);
    }

    //EFFECT: parses wildlife from JSON object and returns it
    private Wildlife jsonToWildlife(JSONObject jsonObject) {
        String wildlifeID = jsonObject.getString("wildlifeID");
        String speciesName = jsonObject.getString("speciesName");
        ConservationStatus conservationStatus = ConservationStatus.valueOf(jsonObject.getString("conservationStatus"));
        LocalDate admissionDate = DateFormatter.strToLocalDate(jsonObject.getString("admissionDate"));
        boolean isFullyFunded = jsonObject.getBoolean("isFullyFunded");
        double targetFunding = jsonObject.getDouble("targetFunding");
        double amountFunded = jsonObject.getDouble("amountFunded");
        String descriptionContent = jsonObject.getString("description");
        Description description = new Description(descriptionContent);
        LocalDate dateFullyFunded = DateFormatter.strToLocalDate(jsonObject.getString("dateFullyFunded"));
        JSONArray donorList = jsonObject.getJSONArray("listOfDonors");
        JSONArray donationsList = jsonObject.getJSONArray("donationRecords");
        List<Donor> listOfDonors = jsonToDonorsList(donorList);
        List<Donation> donationRecords = jsonToDonationsList(donationsList);
        Wildlife wl = new Wildlife(speciesName,targetFunding,conservationStatus, admissionDate);
        wl.setDescription(description);
        wl.setWildlifeID(wildlifeID);
        wl.setAmountFunded(amountFunded);
        wl.setListOfDonors(listOfDonors);
        wl.setDonationRecords(donationRecords);
        wl.setIsFullyFunded(isFullyFunded);
        wl.setDateFullyFunded(dateFullyFunded);
        return wl;
    }


    //EFFECT: parses an array list of donations from JSON object and returns it
    private List<Donation> jsonToDonationsList(JSONArray jsonArray) {
        List<Donation> donationsList = new ArrayList<>();
        for (Object donation: jsonArray) {
            JSONObject jsonDonation = (JSONObject) donation;
            double amount = jsonDonation.getDouble("amount");
            String wildlifeID = jsonDonation.getString("wildlifeID");
            LocalDate dateDonationMade = DateFormatter.strToLocalDate(jsonDonation.getString("dateDonationMade"));
            Donation d = new Donation(wildlifeID, amount);
            d.setDateDonationMade(dateDonationMade);
            donationsList.add(d);
        }
        return donationsList;
    }

    //EFFECT: parses Donor from JSON object and returns it
    private Donor jsonToDonor(JSONObject donorJson) {
        String donorID = donorJson.getString("donorID");
        LocalDate profileCreationDate = DateFormatter.strToLocalDate(donorJson.getString("profileCreationDate"));
        double totalFundingDonated = donorJson.getDouble("totalFundingDonated");
        JSONArray donationsList = donorJson.getJSONArray("recordsOfDonations");
        List<Donation> recordsOfDonations = jsonToDonationsList(donationsList);
        String emailAddress = donorJson.getString("emailAddress");

        Donor donor = new Donor(donorID, emailAddress);
        donor.setRecordsOfDonations(recordsOfDonations);
        donor.setTotalFundingDonated(totalFundingDonated);
        donor.setProfileCreationDate(profileCreationDate);
        return donor;
    }


    //EFFECTS: parses an array list of donor objects from JSON object and returns it
    private List<Donor> jsonToDonorsList(JSONArray jsonArray) {
        List<Donor> donorsList = new ArrayList<>();
        for (Object donor: jsonArray) {
            JSONObject jsonDonor = (JSONObject) donor;
            String donorID = jsonDonor.getString("donorID");
            LocalDate profileCreationDate = DateFormatter.strToLocalDate(jsonDonor.getString("profileCreationDate"));
            double totalFundingDonated = jsonDonor.getDouble("totalFundingDonated");
            String emailAddress = jsonDonor.getString("emailAddress");
            JSONArray donationsList = jsonDonor.getJSONArray("recordsOfDonations");
            List<Donation> recordsOfDonations = jsonToDonationsList(donationsList);
            Donor d = new Donor(donorID, emailAddress);
            d.setProfileCreationDate(profileCreationDate);
            d.setTotalFundingDonated(totalFundingDonated);
            d.setRecordsOfDonations(recordsOfDonations);
            donorsList.add(d);
        }
        return donorsList;
    }



    //EFFECT: reads source file as String and returns it
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    


}
