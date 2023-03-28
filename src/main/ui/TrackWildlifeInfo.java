package ui;

import model.Wildlife;
import model.formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class TrackWildlifeInfo extends JPanel implements ActionListener, Exitable,
        Updatable {
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JButton trackButton;
    private JComboBox wildlifeOptions;
    private JLabel wildlifeInfoSummary;
    private JLabel alertUserNoWl;

    public TrackWildlifeInfo(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.add(createGoBackButton());
        this.add(createTrackWlButton());
        this.add(createWlOptions());
        this.add(createWlInfoLabel());
        this.add(createAlertUserNoWildlife());
        this.setBounds(0, 141, 700, 700);
        this.setBackground(new Color(204, 255, 255));
        this.setLayout(null);
        this.setVisible(false);
    }

    private JButton createTrackWlButton() {
        trackButton = new JButton("Track wildlife info");
        trackButton.setBounds(525, 130, 160, 40);
        trackButton.addActionListener(this);
        return trackButton;
    }

    private JLabel createAlertUserNoWildlife() {
        alertUserNoWl = new JLabel();
        alertUserNoWl.setText("Currently, no wildlife is accepting donations! ");
        alertUserNoWl.setFont(new Font("Comic Sans", Font.BOLD, 18));
        alertUserNoWl.setBounds(126, 120, 500, 25);
        return alertUserNoWl;
    }

    private void refreshWlInfoSummary(Wildlife wl) {
        if (wl == null) {
            wildlifeInfoSummary.setText("");
        } else {
            String id = wl.getWildlifeID();
            String speciesName = wl.getSpeciesName();
            String amountRaised = "$" + wl.getAmountFunded();
            String targetFunding = "$" + wl.getTargetFunding();
            String dateFullyFunded = DateFormatter.toStringLocalDate(wl.getDateFullyFunded());
            String admissonDate = DateFormatter.toStringLocalDate(wl.getAdmissionDate());
            String cs = wl.getConservationStatus().toString();
            String description = wl.getDescription();
            String msg = "<html>Wildlife ID: " + id + "<br>Species Name: " + speciesName + "<br>Conservation Status: "
                    + cs + "<br>Funding Raised: " + amountRaised + "<br>Target Funding: " + targetFunding
                    + "<br>Admission Date: " + admissonDate + "<br>Date Fully Funded: " + dateFullyFunded
                    + "<br>Description: " + "<br>" + description + "</html>";
            System.out.println(msg);
            wildlifeInfoSummary.setText(msg);
        }
    }


    private JLabel createWlInfoLabel() {
        wildlifeInfoSummary = new JLabel();
        wildlifeInfoSummary.setBounds(95, 180, 435, 300);
        wildlifeInfoSummary.setFont(new Font("Comic Sans", Font.BOLD, 15));
        wildlifeInfoSummary.setBackground(new Color(204, 255, 255));
        wildlifeInfoSummary.setForeground(Color.black);
        wildlifeInfoSummary.setVisible(true);
        return wildlifeInfoSummary;
    }

    private Wildlife findWlFromSelection() {
        String selection = wildlifeOptions.getSelectedItem().toString().substring(0,6);

        Map<String, Wildlife> wildlifeMap = new HashMap<>();
        for (Wildlife wl: mainFrame.getCs().getListOfAllWL()) {
            wildlifeMap.put(wl.getWildlifeID(), wl);
        }
        return wildlifeMap.get(selection);
    }


    public JComboBox createWlOptions() {
        JLabel label = new JLabel("Please select a wildlife to view: ");
        label.setBounds(95,100, 500, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.add(label);
        String[] list = new String[0];
        wildlifeOptions = new JComboBox(list);
        wildlifeOptions.setBounds(90, 130, 435, 40);
        return wildlifeOptions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(trackButton)) {
            refreshWlInfoSummary(findWlFromSelection());
        }
        if (actionSource.equals(backToPreviousButton)) {
            wildlifeOptions.setSelectedItem("-");
            this.setVisible(false);
            mainFrame.getForAdmin().setVisible(true);
        }
    }

    @Override
    public JButton createGoBackButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 611, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", 1, 12));
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
            alertUserNoWl.setVisible(true);
        } else {
            alertUserNoWl.setVisible(false);
        }
    }
}
