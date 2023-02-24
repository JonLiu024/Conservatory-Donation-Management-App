package ui;

import formatters.DateFormatter;
import model.*;


import java.time.LocalDate;
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
                System.out.println("Please enter a valid response!");
            }
        }
    }

    private void processCommandAdmin() {
        displayAdminMenu();
        String command = "";
        while (!command.equals("q")) {
            command = scanner.next().toString().toLowerCase();
            if (command.equals("1")) {
                AddNewWildlife();
            } else if (command.equals("2")) {
                removeAWildlife();
            } else if (command.equals("3")) {
                trackDonorInfo();
            } else if (command.equals("4")) {
                viewWildlifeInfo();
            } else if (command.equals("q")) {
                System.out.println("Going back to the previous menu");
            } else {
                System.out.println("Please enter a valid response!");
            }
        }
    }

    private void AddNewWildlife() {
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
        System.out.println("Conservation status: "+ wildlife.getConservationStatus());
        System.out.println("Admission date: " + wildlife.getAdmissionDate());

    }




    private String createSpeciesName(){
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
        while (!command.equals("y")&&!command.equals("n")) {
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
        while (!command.equals("1")&!command.equals("2")&!command.equals("3")&!command.equals("4")
                &!command.equals("5")&!command.equals("6")&!command.equals("7")) {
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
    }
    private void trackDonorInfo() {
        conservationSite.appendlistOfDonors();
        for (Donor donor: conservationSite.getListOfDonors()){
            dis
        }
    }

    private void viewWildlifeInfo() {

    }


    private void makeDonation() {}
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
        System.out.println("3. track donor information");
        System.out.println("4. view all wildlife information");
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
    public void displayConservationTypeMenu() {
        System.out.println("\nPlease select the conservation type for this wildlife:");
        System.out.println("1. Grasslands (GT)");
        System.out.println("2. Forests (FT)");
        System.out.println("3. Desserts (DT)");
        System.out.println("4. Mountains (MT)");
        System.out.println("5. Polar regions (PR)");
        System.out.println("6. Aquatic habitats (AQ)");
        System.out.println("Please enter your choice:");

    }

    public void displayDonorMenu() {
        System.out.println("\nDonor menu:");
        System.out.println("1. Make donations");
        System.out.println("2. Track all your donations");
        System.out.println("3. View wildlife information");
    }




    public void displayWelcomeStatement() {
        System.out.println("Welcome to our wildlife conservation facilities! Here we provide rescue and " +
                "rehabilitation services to injured wildlife. " +
                "\nPlease use the menu to navigate your usage");
    }




}
