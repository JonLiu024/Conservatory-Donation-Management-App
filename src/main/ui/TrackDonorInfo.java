package ui;

import model.Donor;
import model.formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


//Representing a JPanel and the function to track donor information
public class TrackDonorInfo extends JPanel implements Exitable, ActionListener, Updatable, Resetable {
    private final LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JButton trackButton;
    private JComboBox donorListOptions;
    private JLabel donorInfoSummary;
    private JLabel mostGenerousDonorLabel;
    private JButton mostGenerousDonorButton;


    //REQUIRES: mainFrame is not null
    //EFFECTS: creates a TrackDonorInfo panel, sets mainFrame and add associated components
    public TrackDonorInfo(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createGoBackButton());
        this.add(createTrackButton());
        this.add(createDonorOptions());
        this.add(createDonorInfoLabel());
        this.add(createMostGenerousButton());
        this.add(createMostGenerousDonorLabel());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(trackButton)) {
            refreshDonorInfoSummary(findDonorFromSelection());
        }
        if (actionSource.equals(backToPreviousButton)) {
            donorListOptions.setSelectedIndex(0);
            this.setVisible(false);
            clearContents();
            mainFrame.getForAdmin().setVisible(true);
        }
        if (actionSource.equals(mostGenerousDonorButton)) {
            refreshMostGenerousDonor();
        }
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the label to show the donor information
    private JLabel createDonorInfoLabel() {
        donorInfoSummary = new JLabel();
        donorInfoSummary.setBounds(95, 160, 435, 120);
        donorInfoSummary.setFont(new Font("Comic Sans", Font.BOLD, 15));
        donorInfoSummary.setBackground(new Color(204, 255, 255));
        donorInfoSummary.setForeground(Color.black);
        donorInfoSummary.setVisible(true);
        return donorInfoSummary;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the label to show the donor ID of the most generous donor in the conservatory
    private JLabel createMostGenerousDonorLabel() {
        mostGenerousDonorLabel = new JLabel();
        mostGenerousDonorLabel.setBounds(95, 370, 500, 120);
        mostGenerousDonorLabel.setBackground(new Color(204, 255, 255));
        mostGenerousDonorLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        mostGenerousDonorLabel.setForeground(Color.black);
        mostGenerousDonorLabel.setVisible(true);
        return mostGenerousDonorLabel;
    }

    //EFFECTS: updates the text of the donorInfoSummary based on the donor information of d
    private void refreshDonorInfoSummary(Donor d) {
        if (d == null) {
            donorInfoSummary.setText("");
        } else {
            String id = d.getDonorID();
            String email = d.getEmailAddress();
            String totalDonation = "$" + d.getTotalFundingDonated();
            String profileCreationDate = DateFormatter.toStringLocalDate(d.getProfileCreationDate());
            String msg = "<html>Donor ID: " + id + "<br>Email Address: " + email + "<br>Total Donation Amount: "
                    + totalDonation + "<br>Profile Creation Date: " + profileCreationDate + "</html>";
            donorInfoSummary.setText(msg);
        }
    }


    //EFFECTS: updates the text of the mostGenerousDonorLabel based on the list of the most generous donors
    private void refreshMostGenerousDonor() {
        if (mainFrame.getCs().getListOfDonors().size() == 0) {
            String msg = "Currently there is no donor!";
            mostGenerousDonorLabel.setText(msg);
        } else {
            List<Donor> mostGenerousDonorList = mainFrame.getCs().mostGenerousDonor();
            String msg = "<html>Currently, the most generous donor(s) in our conservatory are: ";
            for (Donor d: mostGenerousDonorList) {
                msg = msg + "<br>Donor ID: " + d.getDonorID();
            }
            System.out.println(msg);
            mostGenerousDonorLabel.setText(msg);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the JButton for the user to display the ID list of most generous donors
    private JButton createMostGenerousButton() {
        mostGenerousDonorButton = new JButton("Most generous donor");
        mostGenerousDonorButton.setBounds(525, 320, 160, 40);
        mostGenerousDonorButton.addActionListener(this);
        return mostGenerousDonorButton;
    }

    //EFFECTS: finds and returns the donor based on the selected ID of the donorList
    private Donor findDonorFromSelection() {
        String selection = donorListOptions.getSelectedItem().toString();
        Map<String, Donor> donorMap = new HashMap<>();
        for (Donor d: mainFrame.getCs().getListOfDonors()) {
            donorMap.put(d.getDonorID(), d);
        }
        return donorMap.get(selection);
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the JCombobox for the user to select a donor profile to view
    public JComboBox createDonorOptions() {
        JLabel label = new JLabel("Please select the donor profiles for view: ");
        label.setBounds(95,100, 500, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.add(label);
        String[] list = new String[0];
        donorListOptions = new JComboBox(list);
        donorListOptions.setBounds(90, 130, 435, 40);
        return donorListOptions;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the JButton to track the donor information
    public JButton createTrackButton() {
        trackButton = new JButton("Track donor info");
        trackButton.setBounds(525, 130, 160, 40);
        trackButton.addActionListener(this);
        return trackButton;
    }

    @Override
    public JButton createGoBackButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 611, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        backToPreviousButton.setVisible(true);
        backToPreviousButton.addActionListener(this);
        return backToPreviousButton;
    }

    @Override
    public void refreshComboBoxOptions() {
        int numOfDonors = mainFrame.getCs().getListOfDonors().size();
        String[] list = new String[numOfDonors + 1];
        list[0] = "-";
        for (int i = 0; i < numOfDonors; i++) {
            String option = mainFrame.getCs().getDonorIDList().get(i);
            list[i + 1] = option;
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list);
        donorListOptions.removeAllItems();
        donorListOptions.setModel(model);
        if (numOfDonors == 0) {
            String msg = "No one has made any donation yet!";
            JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void clearContents() {
        mostGenerousDonorLabel.setText("");
        donorInfoSummary.setText("");
    }


}
