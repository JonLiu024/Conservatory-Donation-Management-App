package ui;

import formatters.DateFormatter;
import model.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FundTrackingApp {


    private Scanner scanner;
    private ConservationSite conservationSite;


    public FundTrackingApp() {


    }

//    public void runApp() {
//        displayWelcomeStatement();
//        boolean keepGoing = true;
//        String command = null;
//        init();
//
//
//        while (keepGoing) {
//            displayUserMenu();
//            command = input.next();
//            command = command.toLowerCase().toString();
//
//            if (command.equals(1)) {
//
//            }
//
//            while (!command.equals(1)|!command.equals(2)|!command.equals(3)) {
//                if
//            }
//
//
//        }
//    }

    private void processCommandToDonate() {
        String command = "";
        while (!command.equals("q")) {
            displayDonorMenu();
            command = scanner.next().toString().toLowerCase();
            if (command.equals("1")) {
                makeDonation();
            } else if (command.equals("2")) {
                trackAllYourDonation();
            } else if (command.equals("3")) {
                viewWildlifeInfo();
            } else if (command.equals("q")) {
                System.out.println("Going back to the previous menu");
            } else {
                System.out.println("Please enter a valid response: ");
            }
        }
    }

    private void processCommandAdmin() {
        displayAdminMenu();
        String command = "";
        while (!command.equals("q")) {
            command = scanner.next().toString().toLowerCase();
            if (command.equals("1")) {
                addNewWildlife();
            } else if (command.equals("2")) {
                removeAWildlife();
            } else if (command.equals("3")) {
                viewDonorInfo();
            } else if (command.equals("4")) {
                viewWildlifeInfo();
            } else if (command.equals("q")) {
                System.out.println("Going back to the previous menu");
            } else {
                System.out.println("Please enter a valid response!");
            }
        }
    }

    private void addNewWildlife() {
        String speciesName = createSpeciesName();
        ConservationStatus conservationStatus = selectConservationStatus();
        double targetFunding = createTargetFunding();
        Description description = createDescription();
        LocalDate admissionDate = createAdmissionDate();
        Wildlife newWildlife = new Wildlife(speciesName,targetFunding,conservationStatus, admissionDate);
        newWildlife.setDescription(description);
        conservationSite.addWildlife(newWildlife);
        addNewWildlifeMsg(newWildlife);
    }

    private void addNewWildlifeMsg(Wildlife wildlife) {
        System.out.println("You have successfully added the following wildlife to our conservation site!");
        System.out.println("WildlifeID: " + wildlife.getWildlifeID());
        System.out.println("Species: " + wildlife.getSpeciesName());
        System.out.println("Target funding: " + wildlife.getTargetFunding());
        System.out.println("Conservation status: " + wildlife.getConservationStatus());
        System.out.println("Admission date: " + wildlife.getAdmissionDate());

    }




    private String createSpeciesName() {
        System.out.println("Please enter the species name of this wildlife:");
        String speciesName = scanner.next();
        speciesName = speciesName.toString();
        return speciesName;
    }

    private LocalDate createAdmissionDate() {
        String strDate = "";
        Pattern pattern = Pattern.compile("20[0-9]{2}-((0[^0^2]|1[0-2])-([0-2][0-9]|3[0-1]))|(02-([0-2][0-9]))");
        boolean matchFound = false;
        while (!matchFound) {
            System.out.println("Please enter the admission date of this wildlife in the correct format (yyyy-MM-dd):");
            strDate = scanner.next();
            Matcher matcher = pattern.matcher(strDate);
            matchFound = matcher.find();
        }
        LocalDate admissionDate = DateFormatter.strToLocalDate(strDate);
        return admissionDate;
    }

    private double createTargetFunding() {
        System.out.println("Please enter the target funding to rescue and shelter this wildlife:");
        double targetFunding = scanner.nextDouble();
        return targetFunding;
    }


    private Description createDescription() {
        String command = "";
        System.out.println("Would you like to add a description for this wildlife?");
        while (!command.equals("y") && !command.equals("n")) {
            System.out.println("Please enter y for Yes, n for No:");
            command = scanner.next().toString().toLowerCase();
        }
        if (command.equals("y")) {
            System.out.println("Please enter the description here:");
            String descriptionContent = scanner.next().toString();
            Description description = new Description(descriptionContent);
            System.out.println("You have added successfully create a description for this wildlife");
            return description;
        } else {
            return null;
        }
    }



    private ConservationStatus selectConservationStatus() {
        displayConservationStatusMenu();
        String command = "";
        while (!command.equals("1") && !command.equals("2") && !command.equals("3") && !command.equals("4")
                && !command.equals("5") && !command.equals("6") && !command.equals("7")) {
            System.out.println("Please enter a valid selection:");
            command = scanner.next().toString().toLowerCase();
        }
        if (command.equals("1")) {
            return ConservationStatus.EW;
        } else if (command.equals("2")) {
            return ConservationStatus.CR;
        } else if (command.equals("3")) {
            return ConservationStatus.EN;
        } else if (command.equals("4")) {
            return ConservationStatus.VU;
        } else if (command.equals("5")) {
            return ConservationStatus.NT;
        } else if (command.equals("6")) {
            return ConservationStatus.CD;
        } else {
            return ConservationStatus.LC;
        }
    }



    private void removeAWildlife() {
//        for (Wildlife wildlife: conservationSite.getListOfAllWL()) {
//            System.out.println(wildlife.getWildlifeID() + ":" + wildlife.getSpeciesName());
//        }
//        System.out.println("Please enter the ID of the wildlife you would like to remove:");
//        String command = scanner.next();


    }

    private void viewDonorInfo() {
        conservationSite.updatelistOfDonors();
        System.out.println(conservationSite.getListOfDonors().size() + "people have made donations to "
                + "our conservation facility.");
        System.out.println("They are listed as follow: ");
        for (Donor donor: conservationSite.getListOfDonors()) {
            System.out.println("Donor ID: " + donor.getDonorID() + "\t" + "Email address: " + donor.getEmailAddress()
                    + "\t" + "profile creation date: "
                    + DateFormatter.toStringLocalDate(donor.getProfileCreationDate())
                    + "\t" + "Total amount donated : $" + donor.getTotalFundingDonated());
        }
    }

    private void viewWildlifeInfo() {
        int numOfWLNotFullyFunded = conservationSite.getListOfWildlifeNotFullyFunded().size();
        int numOfWLFulLyFunded = conservationSite.getWildlifeListFullyFunded().size();
        int totalNumOfWL = numOfWLFulLyFunded + numOfWLNotFullyFunded;
        System.out.println("Our conservation site currently has " + totalNumOfWL + " wildlife");
        System.out.println(numOfWLNotFullyFunded + " wildlife are accepting donation."
                + numOfWLFulLyFunded + " wildlife have been fully funded");
        viewSpecificWildlifeInfo();
    }

    private void viewSpecificWildlifeInfo() {
        displayAllWL();
        String entry = "";
        System.out.println("Please enter the ID for your selected wildlife: ");
        boolean selectionFound = false;
        while (!selectionFound) {
            entry = scanner.next().toString();
            for (Wildlife wildlife: conservationSite.getListOfAllWL()) {
                if (wildlife.getWildlifeID().equals(entry)) {
                    System.out.println("WildlifeID: " + wildlife.getWildlifeID());
                    System.out.println("Species: " + wildlife.getSpeciesName());
                    System.out.println("Funding raised: " + wildlife.getAmountFunded());
                    System.out.println("Target funding: " + wildlife.getTargetFunding());
                    System.out.println("Conservation status: " + wildlife.getConservationStatus());
                    System.out.println("Admission date: " + wildlife.getAdmissionDate());
                    break;
                }
            }
            System.out.println("Please enter an existing wildlife ID, in correct format (XX####):");
        }
    }

    private void displayAllWL() {
        System.out.println("All wildlife are listed here: ");
        for (Wildlife wildlife: conservationSite.getListOfAllWL()) {
            System.out.println(wildlife.getWildlifeID() + " : " + wildlife.getSpeciesName());
        }
    }

    private void displayAllWLNotFullyFunded() {
        System.out.println("These wildlife are currently accepting donations: ");
        for (Wildlife wildlife: conservationSite.getListOfWildlifeNotFullyFunded()) {
            System.out.println(wildlife.getWildlifeID() + " : " + wildlife.getSpeciesName());
        }
    }


    private void makeDonation() {
        displayAllWLNotFullyFunded();
        System.out.println("Please enter the ID for your selected wildlife: ");
        boolean selectionFound = false;
        while (!selectionFound) {
            String command = scanner.next().toString();
            for (Wildlife wildlife: conservationSite.getListOfWildlifeNotFullyFunded()) {
                if (wildlife.getWildlifeID().equals(command)) {
                    System.out.println("Please enter the amount of donations: ");
                    double amount = scanner.nextDouble();
                    wildlife.raiseFund(amount);
                    Donation newDonation = new Donation(wildlife, null, amount);


                    //
                    break;
                }
            }
            System.out.println("Please enter an existing wildlife ID, in correct format (XX####):");
        }


    }


    private Donor createDonorProfile() {
        String donorID = createDonorID();
        String emailAddress = createEmailAddress();
        Donor newDonor = new Donor(donorID, emailAddress);
        return newDonor;
    }


    private String createEmailAddress() {
        String emailAddress = "";
        Pattern p = Pattern.compile("[0-9a-z]+(\\.[0-9a-z]+)*@[a-z0-9]+\\.(com|ca)");
        boolean matchFound = false;
        while (!matchFound) {
            System.out.println("Please enter a valid email address: ");
            emailAddress = scanner.next().toString().toLowerCase();
            Matcher matcher = p.matcher(emailAddress);
            matchFound = matcher.find();
        }
        return emailAddress;
    }


    private String recordDonorID() {
        List<String> donorIDList = new ArrayList<>();
        for (Donor d: conservationSite.getListOfDonors()) {
            donorIDList.add(d.getDonorID());
        }
        System.out.println("Please create your donor user ID if you have previously used our apps " +
                "to make any donation, else please create a user ID and enter it here:  ");
        String entryID = scanner.next().toString();
        if (donorIDList.contains(entryID))
        while (donorIDList.contains(entryID)) {
            System.out.println("The ID you entered already existed, Please create an unique ID: ");
            System.out.println("Please enter your ID: ");
            entryID = scanner.next().toString();
        }
        return entryID;
    }


    private void trackAllYourDonation(){}


//
//    private void adminApp() {
//        boolean keepGoing = true;
//        int command;
//
//        while (!command = 1)
//    }

    private void init() {
        conservationSite = new ConservationSite();
        scanner = new Scanner(System.in);
    }


    public void displayUserMenu() {
        System.out.println("\nPlease enter your intended usage:");
        System.out.println("Enter 1 for Admin");
        System.out.println("Enter 2 to donate");
        System.out.println("Enter q to Back to the welcome page");
    }

    public void displayAdminMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("1. add a new wildlife");
        System.out.println("2. remove a wildlife");
        System.out.println("3. view donor information");
        System.out.println("4. view wildlife information");
        System.out.println("q. Back to the Main menu");
        System.out.println("Please enter your choice:");
    }

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


    public void displayDonorMenu() {
        System.out.println("\nDonor menu:");
        System.out.println("1. Make donations");
        System.out.println("2. Track all your donations");
        System.out.println("3. View wildlife information");
    }




    public void displayWelcomeStatement() {
        System.out.println("Welcome to our wildlife conservation facilities! Here we provide rescue and "
                + "rehabilitation services to injured wildlife. "
                + "\nPlease use the menu to navigate your usage");
    }




}
