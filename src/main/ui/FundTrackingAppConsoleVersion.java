package ui;

import model.formatters.DateFormatter;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Representing the facility administration and fund tracking app
public class FundTrackingAppConsoleVersion {
// ./src/main/ui/media/<file name>
    private static final String JSON_STORE = "./data/conservatory.json"; //sources file's pathname
    private Scanner scanner;   //scanner to capture user input
    private ConservationSite conservationSite; //our conservation site
    private JsonWriter jsonWriter; //JSonWriter to write to file
    private JsonReader jsonReader; //JSonReader to read from file


    //EFFECT: run the fund tracking app
    public FundTrackingAppConsoleVersion() {
        runApp();
    }

    //MODIFIES: this
    //EFFECT: initiate the conservation site and prompts the user to enter the commands to use the functions
    //of the app
    public void runApp() {
        displayWelcomeStatement();
        init();
        boolean keepGoing = true;
        String command = "";
        while (keepGoing) {
            displayUserMenu();
            command = scanner.next().toLowerCase();
            if (command.equals("q")) {
                optionToSave();
                keepGoing = false;
            } else if (command.equals("1")) {
                processCommandAdmin();
            } else if (command.equals("2")) {
                processCommandToDonate();
            } else if (command.equals("s")) {
                saveToFile();
            } else if (command.equals("l")) {
                loadFromFile();
            } else {
                System.out.println("Please enter a valid response: ");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: reminds the user to save the conservatory data to file; save the conservatory
    // data to file if the user responds is "y"
    private void optionToSave() {
        System.out.println("Would you like to save the current state of your conservatory to file?");
        System.out.println("Enter y for Saving, n for quiting the program");
        String command = scanner.next().toLowerCase();
        while (!command.equals("y") && !command.equals("n")) {
            System.out.println("Please enter a valid response: ");
            command = scanner.next().toLowerCase();
        }
        if (command.equals("y")) {
            saveToFile();
        }
    }

    // EFFECTS: saves the conservation site states to file
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(conservationSite);
            jsonWriter.close();
            System.out.println("You have successfully saved " + conservationSite.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads the conservation site from file
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void loadFromFile() {
        try {
            conservationSite = jsonReader.read();
            System.out.println("You have successfully loaded " + conservationSite.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECT: processes user commands to use the functions in the donor menu
    private void processCommandToDonate() {
        String command = "";
        while (!command.equals("q")) {
            displayDonorMenu();
            command = scanner.next().toLowerCase();
            if (command.equals("1")) {
                makeDonation();
            } else if (command.equals("2")) {
                seeAllYourDonations();
            } else if (command.equals("3")) {
                viewWildlifeInfo();
            } else if (command.equals("q")) {
                System.out.println("Going back to the previous menu");
            } else {
                System.out.println("Please enter a valid response: ");
            }
        }
    }

    //MODIFIES: this
    //EFFECT: processes user commands to use the functions in the admin menu
    private void processCommandAdmin() {
        String command = "";
        while (!command.equals("q")) {
            displayAdminMenu();
            command = scanner.next().toLowerCase();
            if (command.equals("1")) {
                addNewWildlife();
            } else if (command.equals("2")) {
                viewDonorInfo();
            } else if (command.equals("3")) {
                viewWildlifeInfo();
            } else if (command.equals("4")) {
                viewFacilityInfo();
            } else if (command.equals("q")) {
                System.out.println("Going back to the previous menu");
            } else {
                System.out.println("Please enter a valid response!");
            }

        }
    }

    //EFFECT: prints out the total number of the wildlife in the facility,
    // the total target funding, and total raised funding
    private void viewFacilityInfo() {
        System.out.println("Our conservation site currently holds " + conservationSite.getListOfAllWL().size()
                + " wildlife");
        System.out.println(conservationSite.getListOfWildlifeNotFullyFunded().size()
                + " wildlife are still accepting donations");
        System.out.println(conservationSite.getWildlifeListFullyFunded().size()
                + " wildlife are fully funded");
        System.out.println("The total target funding of all wildlife is $"
                + conservationSite.targetFundingCalculator());
        System.out.println("$" + conservationSite.fundsRaisedCalculator() + " has been raised from all donations");
    }


    //MODIFIES: this
    //EFFECT: prompts the user to add a new wildlife with its information (species name, conservation status,
    //target funding, description, admission date); prints out a message confirming the addition of the new wildlife
    private void addNewWildlife() {
        String speciesName = createSpeciesName();
        ConservationStatus conservationStatus = selectConservationStatus();
        boolean keepAsking = false;
        double targetFunding = createTargetFunding();
        String description = createDescription();
        LocalDate admissionDate = createAdmissionDate();
        Wildlife newWildlife = new Wildlife(speciesName, targetFunding, conservationStatus, admissionDate);
        newWildlife.setDescription(description);
        conservationSite.addWildlife(newWildlife);
        addNewWildlifeMsg(newWildlife);
    }


    //REQUIRES: wildlife is not null
    //EFFECT: prints out a message that confirms the addition of wildlife and lists its information
    private void addNewWildlifeMsg(Wildlife wildlife) {
        System.out.println("You have successfully added the following wildlife to our conservation site!");
        printWildlifeInfo(wildlife);
    }


    //EFFECT: prompts the admin user to enter the species name of the newly added wildlife and returns it
    private String createSpeciesName() {
        scanner.nextLine();
        System.out.println("Please enter the species name of this wildlife:");
        return scanner.nextLine();
    }

    //EFFECT: prompts the admin user to enter the admission date of the newly added wildlife and returns it
    private LocalDate createAdmissionDate() {
        String strDate = "";
        Pattern pattern = Pattern.compile("20[0-9]{2}-((0[^0^2]|1[0-2])-([0-2][0-9]|3[0-1]))|(02-([0-2][0-9]))");
        Matcher matcher = pattern.matcher(strDate);
        boolean matchFound = matcher.find();
        while (!matchFound) {
            System.out.println("Please enter the admission date of this wildlife in the correct format (yyyy-MM-dd):");
            strDate = scanner.next();
            matcher = pattern.matcher(strDate);
            matchFound = matcher.find();
        }
        return DateFormatter.strToLocalDate(strDate);
    }


    //EFFECTS: prompts the admin user to enter the amount of target funding for the newly added wildlife and returns it
    private double createTargetFunding() throws InputMismatchException {
        System.out.println("Please enter the target funding to rescue and shelter this wildlife:");
        double amount = 0;
        boolean repeat = true;
        while (repeat) {
            try {
                amount = scanner.nextDouble();
                repeat = false;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number: ");
                scanner.next();
                repeat = true;
            }
        }
        return amount;
    }


    //EFFECT: prompts the admin user to create a short description of the newly added wildlife and returns it
    private String createDescription() {
        String command = "";
        System.out.println("Would you like to add a description for this wildlife?");
        while (!command.equals("y") && !command.equals("n")) {
            System.out.println("Please enter y for Yes, n for No:");
            command = scanner.next().toLowerCase();
        }
        if (command.equals("y")) {
            scanner.nextLine();
            System.out.println("Please enter the description here:");
            String description = scanner.nextLine();
            System.out.println("You have added successfully create a description for this wildlife");
            return description;
        } else {
            return "None";
        }
    }


    //EFFECT: prompts the admin user to select the conservation status of the newly added wildlife and returns it
    private ConservationStatus selectConservationStatus() {
        displayConservationStatusMenu();
        String command = "";
        while (!command.equals("1") && !command.equals("2") && !command.equals("3") && !command.equals("4")
                && !command.equals("5") && !command.equals("6") && !command.equals("7")) {
            System.out.println("Please enter a valid selection:");
            command = scanner.next().toLowerCase();
        }
        switch (command) {
            case "1":
                return ConservationStatus.EW;
            case "2":
                return ConservationStatus.CR;
            case "3":
                return ConservationStatus.EN;
            case "4":
                return ConservationStatus.VU;
            case "5":
                return ConservationStatus.NT;
            case "6":
                return ConservationStatus.CD;
            default:
                return ConservationStatus.LC;
        }
    }


    //EFFECT: prints out the number of donors and lists the information of all the donors
    private void viewDonorInfo() {
        System.out.println(conservationSite.getListOfDonors().size() + " people have made donations to "
                + "our conservation facility.");
        if (conservationSite.getListOfDonors().size() != 0) {
            System.out.println("They are listed as follow: ");
            for (Donor donor : conservationSite.getListOfDonors()) {
                System.out.println("Donor ID: " + donor.getDonorID() + "\t" + "Email address: "
                        + donor.getEmailAddress()
                        + "\t" + "profile creation date: "
                        + DateFormatter.toStringLocalDate(donor.getProfileCreationDate())
                        + "\t" + "Total amount donated : $" + donor.getTotalFundingDonated());
            }
        }
    }


    //EFFECT: prints out the number of fully funded and non-fully funded wildlife in the conservation site
    //and prompts the user to select specific wildlife to view its information if the conservation site has at least
    //one wildlife
    private void viewWildlifeInfo() {
        System.out.println("Our conservation site currently has " + conservationSite.getListOfAllWL().size()
                + " wildlife");
        System.out.println(conservationSite.getListOfWildlifeNotFullyFunded().size()
                + " wildlife are accepting donation\n"
                + conservationSite.getWildlifeListFullyFunded().size() + " wildlife have been fully funded");
        if (conservationSite.getListOfAllWL().size() != 0) {
            viewSpecificWildlifeInfo();
        }
    }

    //EFFECT: prompts the user to select a wildlife from the list to view its information
    private void viewSpecificWildlifeInfo() {
        displayAllWL();
        String entry = "";
        System.out.println("Please enter the ID for your selected wildlife: ");
        first:
        while (true) {
            entry = scanner.next();
            for (Wildlife wildlife : conservationSite.getListOfAllWL()) {
                if (wildlife.getWildlifeID().equals(entry)) {
                    printWildlifeInfo(wildlife);
                    break first;
                }
            }
            System.out.println("Please enter an existing wildlife ID, in correct format (XX####):");
        }
    }


    //REQUIRES: wildlife is not null
    //EFFECT: prints out the detailed information of wildlife
    private void printWildlifeInfo(Wildlife wildlife) {
        System.out.println("WildlifeID: " + wildlife.getWildlifeID());
        System.out.println("Species: " + wildlife.getSpeciesName());
        System.out.println("Funding raised: $" + wildlife.getAmountFunded());
        System.out.println("Target funding: $" + wildlife.getTargetFunding());
        System.out.println("Conservation status: " + wildlife.getConservationStatus());
        System.out.println("Admission date: " + wildlife.getAdmissionDate());
        if (wildlife.getIsFullyFunded()) {
            System.out.println("Date fully funded: " + DateFormatter.toStringLocalDate(wildlife.getDateFullyFunded()));
        }
        System.out.println("Description: " + wildlife.getDescription());
    }


    //EFFECT: prints out all the wildlife in the conservation site in the (wildlifeID : species) form if it
    //has at least one wildlife
    private void displayAllWL() {
        System.out.println("***********************************************************");
        if (conservationSite.getListOfAllWL().size() == 0) {
            System.out.println("The conservation site currently has no wildlife!");
        } else {
            System.out.println("The wildlife in the conservation sites are listed as following: ");
            System.out.println("(Wildlife ID : Wildlife Species)");
            for (Wildlife wildlife : conservationSite.getListOfAllWL()) {
                System.out.println(wildlife.getWildlifeID() + " : " + wildlife.getSpeciesName());
            }
        }
        System.out.println("***********************************************************");
    }


    //EFFECT: prints out all the wildlife that are not fully funded in the form of (wildlife ID: wildlife species)
    //prints out message to alert user if there is no wildlife accepting donations
    private void displayAllWLNotFullyFunded() {
        if (conservationSite.getListOfWildlifeNotFullyFunded().size() == 0) {
            System.out.println("There is no wildlife currently accepting donations");
            System.out.println("Please wait for the admin team to create new wildlife profile");
        } else {
            System.out.println("These wildlife are currently accepting donations: ");
            for (Wildlife wildlife : conservationSite.getListOfWildlifeNotFullyFunded()) {
                System.out.println(wildlife.getWildlifeID() + " : " + wildlife.getSpeciesName());
            }
        }
    }

    //MODIFIES: this
    //EFFECT: prints out the list of all non-fully funded wildlife, and prompts the donor users to donate to
    //one wildlife if the conservation site has at least one wildlife
    private void makeDonation() {
        displayAllWLNotFullyFunded();
        if (conservationSite.getListOfAllWL().size() != 0) {
            toDonate();
        }
    }

    //MODIFIES: this
    //EFFECT: prompts the donor users to select a wildlife to donate, then prompts the user to select the
    //amount to donate; then prompts the users to create a donor profile if he does not have one;
    private void toDonate() {
        System.out.println("Please choose a wildlife to donate (enter the wildlifeID): ");
        boolean selectionFound = false;
        first:
        while (!selectionFound) {
            String command = scanner.next();
            for (Wildlife wildlife : conservationSite.getListOfWildlifeNotFullyFunded()) {
                if (wildlife.getWildlifeID().equals(command)) {
                    double userIntendedAmount = selectDonationAmt();
                    Donor donor = getDonor();
                    double actualFundingAmt = donor.makeDonation(wildlife, userIntendedAmount);
                    conservationSite.addDonor(donor);
                    makeDonationMsg(userIntendedAmount, actualFundingAmt, wildlife);
                    if (actualFundingAmt < userIntendedAmount) {
                        conservationSite.moveWildlifeToFullyFundedList(wildlife);
                    }
                    selectionFound = true;
                    break first;
                }
            }
            System.out.println("Please enter the correct format (XX####):");
        }
    }


    //REQUIRES: userIntendedAmount, actualFundingAmt > 0, wildlife is not null
    //EFFECT: prints out the message about the donation the donor completes, the message includes the actual
    //funding amount and the wildlife being funded
    private void makeDonationMsg(double userIntendedAmount, double actualFundingAmt, Wildlife wildlife) {
        if (userIntendedAmount > actualFundingAmt) {
            System.out.println(wildlife.getWildlifeID() + "only needs $" + actualFundingAmt
                    + " to be fully funded!" + "$" + userIntendedAmount
                    + " cannot be fully accepted!");
            System.out.println("$" + actualFundingAmt + " has been accepted, the remaining funds will be returned ");
            System.out.println("You have successfully donated $" + actualFundingAmt + " to "
                    + wildlife.getWildlifeID());
        } else {
            System.out.println("You have successfully donated $" + actualFundingAmt + " to "
                    + wildlife.getWildlifeID());
            System.out.println("Our conservation facility thanks for your donation!");
        }
        System.out.println("Our wildlife conservation facility thanks for your donation!");
    }


    //EFFECT: prompts the donor user to set up a new donor profile if he/she does not have one already,
    // otherwise prompts him/her to find his/her existing donor profile; returns user's donor object
    private Donor getDonor() {
        System.out.println("Do you have an existing donor profile with us? \nEnter y for yes, n for no: ");
        String command = scanner.next();
        while (!command.equals("y") && !command.equals("n")) {
            System.out.println("Please enter a valid response: ");
            command = scanner.next();
        }
        if (command.equals("y")) {
            return findDonorFromList();
        } else {
            return createDonorProfile();
        }
    }


    //EFFECT: prompts the donor users to enter his/her donor ID to find his donor profile (object) and returns
    //it
    private Donor findDonorFromList() {
        Donor donor = null;
        List<String> donorIDList = conservationSite.getDonorIDList();
        if (donorIDList.size() == 0) {
            System.out.println("No one has made donations to our conservation facility yet");
        } else {
            System.out.println("Please enter your donor ID: ");
            String entry = scanner.next();
            while (!donorIDList.contains(entry)) {
                System.out.println("Please enter an existing donor ID: ");
                entry = scanner.next();
            }
            for (Donor d : conservationSite.getListOfDonors()) {
                if (d.getDonorID().equals(entry)) {
                    donor = d;
                }
            }
        }
        return donor;
    }


    //EFFECT: prompts the donor user to create a donor object with their created ID and valid email address
    //and return the donor object
    private Donor createDonorProfile() {
        String donorID = createDonorID();
        String emailAddress = createEmailAddress();
        return new Donor(donorID, emailAddress);
    }

    //EFFECT: prompts the donor user to select a donation amount and returns it, re-prompts the user to
    //enter a valid entry (1, 2, 3, or 4) if he entered an invalid response
    private double selectDonationAmt() {
        System.out.println("Please choose the amount of donation: enter 1 for $50, enter 2 for $100, enter "
                + "3 for $350, enter 4 for $1000:");
        String command = scanner.next();
        while (!command.equals("1") && !command.equals("2") && !command.equals("3")
                && !command.equals("4")) {
            System.out.println("Please enter a valid response: ");
            command = scanner.next();
        }
        if (command.equals("1")) {
            return 50;
        } else if (command.equals("2")) {
            return 100;
        } else if (command.equals("3")) {
            return 350;
        } else {
            return 1000;
        }
    }


    //EFFECT: prompts the user to enter an email address and returns it, re-prompts the
    //user to enter a valid email address
    private String createEmailAddress() {
        String emailAddress = "";
        Pattern p = Pattern.compile("[0-9a-z]+(\\.[0-9a-z]+)*@[a-z0-9]+\\.(com|ca)");
        boolean matchFound = false;
        while (!matchFound) {
            System.out.println("Please enter a valid email address: ");
            emailAddress = scanner.next().toLowerCase();
            Matcher matcher = p.matcher(emailAddress);
            matchFound = matcher.find();
        }
        return emailAddress;
    }


    //EFFECT: prompts the user to create a donor ID and returns this ID, re-prompts the user
    //to enter a non-existing ID if the id he entered already existed
    private String createDonorID() {
        List<String> donorIDList = conservationSite.getDonorIDList();
        System.out.println("Please create your donor user ID and enter it here:  ");
        String entryID = scanner.next();
        while (donorIDList.contains(entryID)) {
            System.out.println("The ID you entered have already existed, Please create another one: ");
            entryID = scanner.next();
        }
        return entryID;
    }


    //EFFECT: prompts the user to find his/her donor profile and prints out the donation records of
    //the user including the amount, wildlife and date and time of the donation
    private void seeAllYourDonations() {
        Donor donor = findDonorFromList();
        if (donor != null) {
            System.out.println(donor.getDonorID() + " has made the following donations: ");
            for (Donation d : donor.getRecordsOfDonations()) {
                System.out.println("$" + d.getAmount() + " was donated to wildlife "
                        + "(ID: " + d.getWildlifeID() + ") on " + d.getDateDonationMade());
            }
        }
    }


    //MODIFIES: this
    //EFFECT: initiates the conservation site and the scanner object to capture user inputs
    private void init() {
        conservationSite = new ConservationSite();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECT: prints out the menu for user to choose the intended usage
    public void displayUserMenu() {
        System.out.println("\nPlease enter your intended usage:");
        System.out.println("Enter 1 for Admin");
        System.out.println("Enter 2 to donate");
        System.out.println("Enter s to save your conservation site data to file");
        System.out.println("Enter l to load your conservation site data from file");
        System.out.println("Enter q to quit the app");
    }


    //EFFECT: prints out the menu for admin user
    public void displayAdminMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("1. add a new wildlife");
        System.out.println("2. view donor information");
        System.out.println("3. view wildlife information");
        System.out.println("4. view facility information");
        System.out.println("q. Back to the Main menu");
        System.out.println("Please enter your choice:");
    }

    //EFFECT: prints out the menu for user to select the conservation status of the added wildlife
    public void displayConservationStatusMenu() {
        System.out.println("\nPlease select the conservation status for this wildlife:");
        System.out.println("1. Extinct in the wild (EW)");
        System.out.println("2. Critically endangered (CR)");
        System.out.println("3. Endangered (EN)");
        System.out.println("4. Vulnerable (VU)");
        System.out.println("5. Near threatened (NT)");
        System.out.println("6. Conservation dependent (CD)");
        System.out.println("7. Least concerned (LC)");

    }

    //prints out the menu for donor user
    public void displayDonorMenu() {
        System.out.println("\nDonor menu:");
        System.out.println("1. make donations");
        System.out.println("2. Track all your donations");
        System.out.println("3. View wildlife information");
        System.out.println("q. Back to te Main menu");
    }


    //EFFECT: prints out the welcome message
    public void displayWelcomeStatement() {
        System.out.println("Welcome to our wildlife conservation facility! Here we provide rescue and "
                + "rehabilitation services to injured wildlife. "
                + "\nPlease use the menu to navigate your usage");
    }

}
