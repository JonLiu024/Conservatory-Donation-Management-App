package ui;

import model.ConservationSite;
import model.ConservationStatus;
import model.TypeConservationSite;

import java.util.Locale;
import java.util.Scanner;

public class FundTrackingApp {


    private Scanner scanner;
    private ConservationSite conservationSiteGL;
    private ConservationSite conservationSiteFS;
    private ConservationSite conservationSiteDT;
    private ConservationSite conservationSiteMT;
    private ConservationSite conservationSitePR;
    private ConservationSite conservationSiteAQ;


    public FundTrackingApp() {
        runApp();
    }

    public void runApp() {
        boolean keepGoing = true;
        String command = null;
        init();


        while (keepGoing) {
            displayUserMenu();
            command = input.next();
            command = command.toLowerCase().toString();

            if (command.equals(1)) {

            }

            while (!command.equals(1)|!command.equals(2)|!command.equals(3)) {
                if
            }


        }
    }

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
        String command = "";
        while (!command.equals("q")) {
            displayAdminMenu();
            command = scanner.next().toString().toLowerCase();
            if (command.equals("1")) {
                addNewWildlife();
            } else if (command.equals("2")) {
                removeAWildlife();
            } else if (command.equals("3")) {
                trackDonorInfo();
            } else if (command.equals("4")) {
                trackWildlifeInfo();
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


    }

    private String createSpeciesName(){
        System.out.println("Please enter the species name of the wildlife:");
        return scanner.next().toString().toLowerCase();
    }



    private ConservationStatus selectConservationStatus() {
        displayConservationStatusMenu();
        String command = "";
        while (!command.equals("1")|!command.equals("2")|!command.equals("3")|
                !command.equals("4")|!command.equals("5")|!command.equals("6")|
                !command.equals("7")) {
            command = scanner.next().toString().toLowerCase();
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
            } else if (command.equals("7")) {
                return ConservationStatus.LC;
            } else {
                System.out.println("Please enter a valid response!");
            }
        }

    }


    private void removeAWildlife() {}
    private void trackDonorInfo() {}
    private void trackWildlifeInfo() {}


    private void makeDonation() {}
    private void trackAllYourDonation(){}
    private void viewWildlifeInfo(){}

//
//    private void adminApp() {
//        boolean keepGoing = true;
//        int command;
//
//        while (!command = 1)
//    }

    private void init() {
        ConservationSite conservationSiteGL = new ConservationSite(TypeConservationSite.GL);
        ConservationSite conservationSiteFS = new ConservationSite(TypeConservationSite.FT);
        ConservationSite conservationSiteDT = new ConservationSite(TypeConservationSite.DT);
        ConservationSite conservationSiteMT = new ConservationSite(TypeConservationSite.MT);
        ConservationSite conservationSitePR = new ConservationSite(TypeConservationSite.PR);
        ConservationSite conservationSiteAQ = new ConservationSite(TypeConservationSite.AQ);
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
        System.out.println("4. track wildlife information");
        System.out.println("q. Back to the Main menu");
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
