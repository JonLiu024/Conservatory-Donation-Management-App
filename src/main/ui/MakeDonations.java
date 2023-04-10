package ui;

import model.Donor;
import model.Wildlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representing the panel and the function to make donations
public class MakeDonations extends JPanel implements ActionListener, Exitable, Updatable {


    private JComboBox wildlifeOptions;
    private JComboBox donationAmount;
    private JLabel alertUserNoWildlife;
    private final FundTrackerAppGUI mainFrame;
    private JButton submissionButton;
    private JButton goBackButton;

    //REQUIRES: mainFrame is not null
    //EFFECTS: creates a MakeDonations object; set the mainFrame and adds the associated Jcomponents
    public MakeDonations(FundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createSubmissionButton());
        this.add(selectDonationAmount());
        this.add(createGoBackButton());
        this.add(createWildlifeOptions());
        this.add(createAlertUserNoWildlife());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the label to alert the user that there is no wildlife in the conservatory
    private JLabel createAlertUserNoWildlife() {
        alertUserNoWildlife = new JLabel();
        alertUserNoWildlife.setText("Currently, no wildlife is accepting donations! ");
        alertUserNoWildlife.setFont(new Font("Comic Sans", Font.BOLD, 18));
        alertUserNoWildlife.setBounds(126, 120, 500, 25);
        return alertUserNoWildlife;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the Jcombobox for the user to select the wildlife to donate
    private JComboBox createWildlifeOptions() {
        JLabel label = new JLabel("Please select a wildlife to donate");
        label.setBounds(150, 200, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        wildlifeOptions = new JComboBox(new String[0]);
        wildlifeOptions.setBounds(150, 226, 400, 25);
        return wildlifeOptions;
    }



    @Override
    public void refreshComboBoxOptions() {
        int numbOfWl = mainFrame.getCs().getListOfWildlifeNotFullyFunded().size();
        String[] wildlifeList = new String[numbOfWl];
        for (int i = 0; i < numbOfWl; i++) {
            String option = mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getWildlifeID() + ":"
                    + mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getSpeciesName();
            wildlifeList[i] = option;
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(wildlifeList);
        wildlifeOptions.removeAllItems();
        wildlifeOptions.setModel(model);
        if (numbOfWl == 0) {
            alertUserNoWildlife.setVisible(true);
        } else {
            alertUserNoWildlife.setVisible(false);
        }
    }


    //MODIFIES: this
    //EFFECTS: sets and returns a Jcombobox for the user to select the amount of donations
    private JComboBox selectDonationAmount() {
        JLabel label = new JLabel("Please select the amount of your donation");
        label.setBounds(150, 271, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        String[] donationOptions = {"$50", "$100", "$200", "$350", "$500"};
        donationAmount = new JComboBox(donationOptions);
        donationAmount.setBounds(150, 296, 400, 25);
        return donationAmount;
    }


    //MODIFIES: this
    //EFFECTS: sets and returns a Jbutton for the user to submit their donations
    private JButton createSubmissionButton() {
        submissionButton = new JButton("Submit");
        submissionButton.setBounds(490, 611, 100, 40);
        submissionButton.setFocusable(false);
        submissionButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        submissionButton.setBackground(Color.pink);
        submissionButton.addActionListener(this);
        return submissionButton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.equals(submissionButton)) {
            donate();
            this.setVisible(false);
        }
        if (source.equals(goBackButton)) {
            this.setVisible(false);
            mainFrame.getToDonate().setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts a show confirmation window asking the user if he/she has an existing donor ID, if yes, prompts
    //the user to enter the donor ID and makes the donations to the chosen wildlife, if no, show the user the page to
    //create donor profile
    //EFFECTS: https://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
    public void donate() {
        String msg = "Do you have a donor ID with us?";
        int reply = JOptionPane.showConfirmDialog(null, msg, "Donor profile",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            this.setVisible(false);
            mainFrame.getCp().setVisible(true);
        } else {
            double userIntendedAmt = optionToAmt(donationAmount.getSelectedItem().toString());
            Wildlife wl = optionToWL(wildlifeOptions.getSelectedItem().toString());
            Donor donor = getDonor();
            double amount = donor.makeDonation(wl, userIntendedAmt);
            if (wl.getAmountFunded() == wl.getTargetFunding()) {
                mainFrame.getCs().moveWildlifeToFullyFundedList(wl);
                String message = wl.getWildlifeID() + " only needs " + "$" + amount + " to be fully funded! ";
                JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
            }
            mainFrame.getCs().addDonor(donor);
            mainFrame.getCs().updateRaisedFunding();
            mainFrame.getToDonate().setVisible(true);
            String message = "You have successfully donated " + "$" + amount + " to " + wl.getWildlifeID();
            JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //REQUIRES: the length of the str > 6
    //EFFECTS: truncates str to a 6 character wildlife ID string, finds the wildlife object and returns it
    private Wildlife optionToWL(String str) {
        String wildlifeID = str.substring(0, 6);
        for (Wildlife wl : mainFrame.getCs().getListOfWildlifeNotFullyFunded()) {
            if (wildlifeID.equals(wl.getWildlifeID())) {
                return wl;
            }
        }
        return null;
    }


    //EFFECTS: removes the "$" char at the front of the str and converts it to a double and returns it
    private double optionToAmt(String str) {
        String amt = str.substring(1);
        return Double.parseDouble(amt);
    }


    //EFFECTS: shows an Input window for the user to enter the donor ID, if the entered ID does not exisit, keeps
    //prompting the user to enter donor ID until an existing ID is entered, finds the donor of the donor ID and
    // returns it
    private Donor getDonor() {
        String userID = JOptionPane.showInputDialog("Please enter your donor ID: ");
        while (!mainFrame.getCs().getDonorIDList().contains(userID)) {
            String msg = "Oops, the ID you entered does not exist, "
                    + "Please enter an existing donor ID: ";
            userID = JOptionPane.showInputDialog(msg, JOptionPane.WARNING_MESSAGE);
        }
        for (Donor d : mainFrame.getCs().getListOfDonors()) {
            if (userID.equals(d.getDonorID())) {
                return d;
            }
        }
        return null;
    }


    @Override
    public JButton createGoBackButton() {
        goBackButton = new JButton("Go back");
        goBackButton.setBounds(590, 611, 100, 40);
        goBackButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        goBackButton.setForeground(Color.black);
        goBackButton.setBackground(Color.PINK);
        goBackButton.setVisible(true);
        goBackButton.addActionListener(this);
        return goBackButton;
    }
}
