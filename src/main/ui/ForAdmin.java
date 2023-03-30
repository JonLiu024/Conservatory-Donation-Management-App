package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Representing a JPanel components of the LauNchFundTrackerAppGUI for admin users to use the app
public class ForAdmin extends JPanel implements ActionListener, Exitable {

    private JButton addWildlifeButton;
    private JButton trackDonorInfoButton;
    private JButton trackWildlifeInfoButton;
    private JButton goBackButton;
    private final LaunchFundTrackerAppGUI mainFrame;



    //REQUIRES: mainFrame is not null
    //EFFECT: constructs a ForAdmin components and sets its associated features (bounds, background colour, layout);
    //sets the mainFrame and adds the associated components
    public ForAdmin(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(700, 840);
        this.setBackground(new Color(204, 255, 255));
        this.add(createAddWildlifeButton());
        this.add(createTrackDonorInfoButton());
        this.add(createTrackWLInfoButton());
        this.add(createGoBackButton());
        this.setLayout(null);
        this.setVisible(false);

    }


    //MODIFIES: this
    //EFFECTS: sets and returns the Jbutton for the user to navigate to the Add a wildlife page
    public JButton createAddWildlifeButton() {
        addWildlifeButton = new JButton();
        ImageIcon addWLImage = new ImageIcon("./data/media/add wildlife .jpg");
        addWildlifeButton.setIcon(addWLImage);
        addWildlifeButton.setBounds(101, 251, 200, 125);
        addWildlifeButton.setText("Add a wildlife");
        addWildlifeButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        addWildlifeButton.setVerticalTextPosition(JButton.BOTTOM);
        addWildlifeButton.setHorizontalTextPosition(JButton.CENTER);
        addWildlifeButton.setIconTextGap(1);
        addWildlifeButton.setForeground(Color.black);
        addWildlifeButton.setBackground(Color.PINK);
        addWildlifeButton.setVisible(true);
        addWildlifeButton.addActionListener(this);
        return addWildlifeButton;
    }



    //MODIFIES: this
    //EFFECTS: sets and returns the Jbutton for the user to navigate to the Track donor info page
    public JButton createTrackDonorInfoButton() {
        trackDonorInfoButton = new JButton();
        ImageIcon addTrackDonorImage = new ImageIcon("./data/media/donor info2 .jpg");
        trackDonorInfoButton.setIcon(addTrackDonorImage);
        trackDonorInfoButton.setBounds(401, 251, 200, 125);
        trackDonorInfoButton.setText("Track donor info");
        trackDonorInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackDonorInfoButton.setVerticalTextPosition(JButton.BOTTOM);
        trackDonorInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackDonorInfoButton.setIconTextGap(1);
        trackDonorInfoButton.setForeground(Color.black);
        trackDonorInfoButton.setBackground(Color.PINK);
        trackDonorInfoButton.setVisible(true);
        trackDonorInfoButton.addActionListener(this);
        return trackDonorInfoButton;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns a Jbutton to navigate the user to the Track Wildlife info page
    public JButton createTrackWLInfoButton() {
        trackWildlifeInfoButton = new JButton();
        ImageIcon trackWLInfoImage = new ImageIcon("./data/media/wildlife info.jpg");
        trackWildlifeInfoButton.setIcon(trackWLInfoImage);
        trackWildlifeInfoButton.setBounds(101, 500, 200, 125);
        trackWildlifeInfoButton.setText("Track wildlife info");
        trackWildlifeInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackWildlifeInfoButton.setVerticalTextPosition(JButton.BOTTOM);
        trackWildlifeInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackWildlifeInfoButton.setIconTextGap(1);
        trackWildlifeInfoButton.setForeground(Color.black);
        trackWildlifeInfoButton.setBackground(Color.PINK);
        trackWildlifeInfoButton.setVisible(true);
        trackWildlifeInfoButton.addActionListener(this);
        return trackWildlifeInfoButton;
    }

    @Override
    public JButton createGoBackButton() {
        goBackButton = new JButton();
        ImageIcon backImage = new ImageIcon("./data/media/back to previous menu .jpg");
        goBackButton.setIcon(backImage);
        goBackButton.setBounds(401, 500, 200, 125);
        goBackButton.setText("Back to the main menu");
        goBackButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        goBackButton.setVerticalTextPosition(JButton.BOTTOM);
        goBackButton.setHorizontalTextPosition(JButton.CENTER);
        goBackButton.setIconTextGap(1);
        goBackButton.setForeground(Color.black);
        goBackButton.setBackground(Color.PINK);
        goBackButton.setVisible(true);
        goBackButton.addActionListener(this);
        return goBackButton;
    }


    //MODIFIES: this
    //EFFECTS: sets the main menu JComponents visible
    private void setMainFrameVisible() {
        mainFrame.getAdminButton().setVisible(true);
        mainFrame.getDonationButton().setVisible(true);
        mainFrame.getSaveButton().setVisible(true);
        mainFrame.getLoadButton().setVisible(true);
        mainFrame.getBackgroundLabel().setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(addWildlifeButton)) {
            this.setVisible(false);
            mainFrame.getAw().setVisible(true);
        }
        if (actionSource.equals(trackDonorInfoButton)) {
            this.setVisible(false);
            mainFrame.getTrackDonorInfo().refreshComboBoxOptions();
            mainFrame.getTrackDonorInfo().setVisible(true);
        }
        if (actionSource.equals(trackWildlifeInfoButton)) {
            this.setVisible(false);
            mainFrame.getTwlInfo().refreshComboBoxOptions();
            mainFrame.getTwlInfo().setVisible(true);
        }
        if (actionSource.equals(goBackButton)) {
            this.setVisible(false);
            setMainFrameVisible();
        }
    }


}
