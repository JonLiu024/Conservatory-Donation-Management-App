package ui;

import model.Wildlife;
import model.formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class TrackWildlifeInfo extends JPanel implements ActionListener, Exitable,
        Updatable, Resetable {
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JButton trackButton;
    private JButton trackFullyFundedWlButton;
    private JComboBox wildlifeOptions;
    private JLabel wildlifeInfoSummary;
    private JLabel fullyFundedWlLabel;


    public TrackWildlifeInfo(LaunchFundTrackerAppGUI mainFrame) {
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

    private JButton createTrackWlButton() {
        trackButton = new JButton("Track wildlife info");
        trackButton.setBounds(525, 50, 160, 40);
        trackButton.addActionListener(this);
        return trackButton;
    }

    private JButton createTrackFFWlButton() {
        trackFullyFundedWlButton = new JButton("Fully funded wildlife");
        trackFullyFundedWlButton.setBounds(525, 360, 160, 40);
        trackFullyFundedWlButton.addActionListener(this);
        return trackFullyFundedWlButton;
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
            System.out.println(msg);
            wildlifeInfoSummary.setText(msg);
        }
    }


    private JLabel createWlInfoLabel() {
        wildlifeInfoSummary = new JLabel();
        wildlifeInfoSummary.setBounds(95, 50, 435, 300);
        wildlifeInfoSummary.setFont(new Font("Comic Sans", Font.BOLD, 15));
        wildlifeInfoSummary.setBackground(new Color(204, 255, 255));
        wildlifeInfoSummary.setForeground(Color.black);
        wildlifeInfoSummary.setVisible(true);
        return wildlifeInfoSummary;
    }

    private JLabel createFfWlLabel() {
        fullyFundedWlLabel = new JLabel();
        fullyFundedWlLabel.setBounds(95, 365, 500, 120);
        fullyFundedWlLabel.setBackground(new Color(204, 255, 255));
        fullyFundedWlLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        fullyFundedWlLabel.setForeground(Color.black);
        fullyFundedWlLabel.setVisible(true);
        return fullyFundedWlLabel;
    }

    private void refreshFullyFundedWlLabel() {
        List<Wildlife> fullyFundedList = mainFrame.getCs().getWildlifeListFullyFunded();
        String msg = "<html>Currently, There are " + fullyFundedList.size()
                + " fully funded wildlife in the conservatory: ";
        for (Wildlife wl : fullyFundedList) {
            msg += "<br>Wildlife ID (Species Name): " + wl.getWildlifeID() + " (" + wl.getSpeciesName() + ")";
        }
        msg += "</html>";
        System.out.println(msg);
        fullyFundedWlLabel.setText(msg);
    }

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
