package ui;

import model.ConservationSite;
import model.ConservationStatus;
import model.TypeConservationSite;

import java.util.Locale;
import java.util.Scanner;

public class FundTrackingApp {


    public FundTrackingApp() {
        runApp();
    }

    public void runApp() {
        boolean keepGoing = true;
        String command = null;
        init();
        Scanner input = new Scanner(System.in);

        while (keepGoing) {
            displayUserMenu();
            command = input.next();
            command = command.toLowerCase();

            while (!command.equals(1)|!command.equals(2)|!command.equals(3)) {
                if
            }


        }
    }

    private void init() {
        ConservationSite conservationSiteGL = new ConservationSite(TypeConservationSite.GL);
        ConservationSite conservationSiteFS = new ConservationSite(TypeConservationSite.FT);
        ConservationSite conservationSiteDT = new ConservationSite(TypeConservationSite.DT);
        ConservationSite conservationSiteMT = new ConservationSite(TypeConservationSite.MT);
        ConservationSite conservationSitePR = new ConservationSite(TypeConservationSite.PR);
        ConservationSite conservationSiteAQ = new ConservationSite(TypeConservationSite.AQ);

    }


    public void displayUserMenu() {
        System.out.println("\nPlease enter your intended usage:");
        System.out.println("Enter 1 for Admin");
        System.out.println("Enter 2 to donate");
        System.out.println("Enter 3 to quit");
    }

    public void displayAdminMenu() {
        System.out.println("\nAdmin menu");
        System.out.println("1. rescue a new wildlife");
        System.out.println("2. remove a wildlife");
        System.out.println("3. track donor information");
        System.out.println("4. track wildlife information");
        System.out.println("5. Back to the Main menu");
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
        System.out.println("2. See all your donations");
        System.out.println("3. View wildlife information");
    }




    public void displayWelcomeStatement() {
        System.out.println("Welcome to our wildlife conservation facilities! Here we provide rescue and " +
                "rehabilitation services to injured wildlife. " +
                "\nPlease use the menu to navigate your usage");
    }




}
