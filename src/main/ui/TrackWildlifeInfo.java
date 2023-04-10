package ui;

import model.Wildlife;
import formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

//Representing the JPanel and the function to track wildlife information
public class TrackWildlifeInfo extends JPanel implements ActionListener, Exitable,
        Updatable, Resetable {
    private final FundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JButton trackButton;
    private JButton trackFullyFundedWlButton;
    private JComboBox wildlifeOptions;
    private JLabel wildlifeInfoSummary;
    private JLabel fullyFundedWlLabel;

    //REQUIRES: mainFrame is not null
    //EFFECTS: creates a TrackWildlifeInfo object, sets the mainFrame, and adds the associated JComponents
    public TrackWildlifeInfo(FundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.add(createGoBackButton());
        this.add(createTrackWlButton());
        this.add(createWlOptions());
        this.add(createWlInfoLabel());
        this.add(createFfWlLabel());
        this.add(createTrackFFWlButton());
        this.setBounds(0, 141, 700, 700);
        this.setBackground(new Color(204, 255, 255));
        this.setLayout(null);
        this.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the button to track the wildlife information
    private JButton createTrackWlButton() {
        trackButton = new JButton("Track wildlife info");
        trackButton.setBounds(525, 50, 160, 40);
        trackButton.addActionListener(this);
        return trackButton;
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the button to show the ID list of the fully funded wildlife
    private JButton createTrackFFWlButton() {
        trackFullyFundedWlButton = new JButton("Fully funded wildlife");
        trackFullyFundedWlButton.setBounds(525, 360, 160, 40);
        trackFullyFundedWlButton.addActionListener(this);
        return trackFullyFundedWlButton;
    }

    //MODIFIES: this
    //EFFECTS: updates the text contents of wildlifeInfoSummary based on the information of wl
    private void refreshWlInfoSummary(Wildlife wl) {
        if (wl == null) {
            wildlifeInfoSummary.setText("");
        } else {
            String id = wl.getWildlifeID();
            String speciesName = wl.getSpeciesName();
            String amountRaised = "$" + wl.getAmountFunded();
            String targetFunding = "$" + wl.getTargetFunding();
            String dateFullyFunded = DateFormatter.toStringLocalDate(wl.getDateFullyFunded());
            if (dateFullyFunded.equals("null")) {
                dateFullyFunded = "None";
            }
            String admissonDate = DateFormatter.toStringLocalDate(wl.getAdmissionDate());
            String cs = wl.getConservationStatus().toString();
            String description = wl.getDescription();
            String msg = "<html>Wildlife ID:" + id + "<br>Species Name:" + speciesName
                    + "<br>Conservation Status:"
                    + cs + "<br>Funding Raised:" + amountRaised + "<br>Target Funding:" + targetFunding
                    + "<br>Admission Date:" + admissonDate + "<br>Date Fully Funded:" + dateFullyFunded
                    + "<br>Description:" + "<br>" + description + "</html>";
            wildlifeInfoSummary.setText(msg);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets and returns a JLabel to show the wildlife information
    private JLabel createWlInfoLabel() {
        wildlifeInfoSummary = new JLabel();
        wildlifeInfoSummary.setBounds(95, 50, 435, 300);
        wildlifeInfoSummary.setFont(new Font("Comic Sans", Font.BOLD, 15));
        wildlifeInfoSummary.setBackground(new Color(204, 255, 255));
        wildlifeInfoSummary.setForeground(Color.black);
        wildlifeInfoSummary.setVisible(true);
        return wildlifeInfoSummary;
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the JLabel to show fully funded wildlife list
    private JLabel createFfWlLabel() {
        fullyFundedWlLabel = new JLabel();
        fullyFundedWlLabel.setBounds(95, 365, 500, 120);
        fullyFundedWlLabel.setBackground(new Color(204, 255, 255));
        fullyFundedWlLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        fullyFundedWlLabel.setForeground(Color.black);
        fullyFundedWlLabel.setVisible(true);
        return fullyFundedWlLabel;
    }

    //MODIFIES: this
    //EFFECTS: updates the text contents of fullyFundedWlLabel based on the information of
    // cs's list of fully funded
    private void refreshFullyFundedWlLabel() {
        List<Wildlife> fullyFundedList = mainFrame.getCs().getWildlifeListFullyFunded();
        String msg = "<html>Currently, There are " + fullyFundedList.size()
                + " fully funded wildlife in the conservatory: ";
        for (Wildlife wl : fullyFundedList) {
            msg += "<br>Wildlife ID (Species Name): " + wl.getWildlifeID() + " (" + wl.getSpeciesName() + ")";
        }
        msg += "</html>";
        fullyFundedWlLabel.setText(msg);
    }

    //EFFECT: finds and return the wildlife object from the user input of wildlife ID
    private Wildlife findWlFromSelection() {
        String selection = wildlifeOptions.getSelectedItem().toString();
        if (selection.length() > 6) {
            selection = selection.substring(0, 6);
        }
        Map<String, Wildlife> wildlifeMap = new HashMap<>();
        for (Wildlife wl : mainFrame.getCs().getListOfAllWL()) {
            wildlifeMap.put(wl.getWildlifeID(), wl);
        }
        return wildlifeMap.get(selection);
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the JLabel for the user to select the wildlife to view
    public JComboBox createWlOptions() {
        JLabel label = new JLabel("Please select a wildlife to view: ");
        label.setBounds(95, 25, 500, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.add(label);
        String[] list = new String[0];
        wildlifeOptions = new JComboBox(list);
        wildlifeOptions.setBounds(90, 50, 435, 40);
        return wildlifeOptions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(trackButton)) {
            refreshWlInfoSummary(findWlFromSelection());
        }
        if (actionSource.equals(trackFullyFundedWlButton)) {
            refreshFullyFundedWlLabel();
        }
        if (actionSource.equals(backToPreviousButton)) {
            wildlifeOptions.setSelectedItem("-");
            clearContents();
            this.setVisible(false);
            mainFrame.getForAdmin().setVisible(true);
        }
    }


    @Override
    public JButton createGoBackButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 611, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        backToPreviousButton.setForeground(Color.black);
        backToPreviousButton.setBackground(Color.PINK);
        backToPreviousButton.setVisible(true);
        backToPreviousButton.addActionListener(this);
        return backToPreviousButton;
    }

    @Override
    public void refreshComboBoxOptions() {
        int numOfWl = mainFrame.getCs().getListOfAllWL().size();
        String[] list = new String[numOfWl + 1];
        list[0] = "-";
        for (int i = 0; i < numOfWl; i++) {
            String id = mainFrame.getCs().getListOfAllWL().get(i).getWildlifeID();
            String speciesName = mainFrame.getCs().getListOfAllWL().get(i).getSpeciesName();
            String option = id + ":" + speciesName;
            list[i + 1] = option;
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list);
        wildlifeOptions.removeAllItems();
        wildlifeOptions.setModel(model);
        if (numOfWl == 0) {
            String msg = "Currently, no wildlife is accepting donations! ";
            JOptionPane.showMessageDialog(null, msg, "Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void clearContents() {
        fullyFundedWlLabel.setText("");
        wildlifeInfoSummary.setText("");
    }
}
