package ui;

import model.ConservationSite;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LaunchFundTrackerAppGUI extends JFrame implements ActionListener, CommonComponents {

    private JPanel titlePanel;
    private ForAdmin forAdmin;
    private ToDonate toDonate;
    private AddWildlife aw;
    private MakeDonations md;
    private CreateDonorProfile cp;
    private TrackDonationsRecords trackDonationsRecords;
    private TrackDonorInfo trackDonorInfo;
    private TrackWildlifeInfo trackWildlifeInfo;
    private JLabel backgroundLabel;
    private JButton adminButton;
    private JButton donationButton;
    private JButton saveButton;
    private JButton loadButton;


    private static final String JSON_STORE = "./data/conservatory.json"; //sources file's pathname
    private ConservationSite cs; //our conservation site
    private JsonWriter jsonWriter; //JSonWriter to write to file
    private JsonReader jsonReader; //JSonReader to read from file


    //MODIFIES: this
    //EFFECT: creates a conservation site, a jsonWriter, and a jsonReader to save or load data to/from file
    private void modelsInitiator() {
        cs = new ConservationSite();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void mainMenuPanelInitiator() {
        backgroundLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./data/media/background2.jpg");
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setBounds(1, 200, 700, 500);
        this.add(createTitlePanel());
        this.add(createDonateButton());
        this.add(createAdminButton());
        this.add(createSaveButton());
        this.add(createLoadButton());
        this.add(backgroundLabel);
    }

    private void panelsInitiator() {
        aw = new AddWildlife(this);
        forAdmin = new ForAdmin(this);
        toDonate = new ToDonate(this);
        md = new MakeDonations(this);
        cp = new CreateDonorProfile(this);
        trackDonationsRecords = new TrackDonationsRecords(this);
        trackDonorInfo = new TrackDonorInfo(this);
        trackWildlifeInfo = new TrackWildlifeInfo(this);


    }

    private void addPanelsToFrame() {
        this.add(forAdmin);
        this.add(toDonate);
        this.add(aw);
        this.add(md);
        this.add(cp);
        this.add(trackDonationsRecords);
        this.add(trackDonorInfo);
        this.add(trackWildlifeInfo);

    }

    private void setMainMenuInvisible() {
        adminButton.setVisible(false);
        saveButton.setVisible(false);
        loadButton.setVisible(false);
        donationButton.setVisible(false);
        backgroundLabel.setVisible(false);
    }


    public LaunchFundTrackerAppGUI() {
        modelsInitiator();
        panelsInitiator();
        mainMenuPanelInitiator();
        addPanelsToFrame();
        this.setTitle("Wildlife Conservatory ");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        this.setVisible(true);
    }


    public JPanel createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 700, 140);
        titlePanel.setBackground(new Color(204, 255, 255));
        JLabel label1 = new JLabel();
        ImageIcon titleImage = new ImageIcon("./data/media/logoWildlifeConservatory250.jpg");
        String labelContent1 = "<html>Welcome to our wildlife conservatory! <BR><BR></html>";
        label1.setText(labelContent1);
        label1.setIcon(titleImage);
        label1.setHorizontalTextPosition(4);
        label1.setFont(new Font("Serif", 1, 33));
        label1.setVerticalAlignment(1);
        titlePanel.add(label1);
        return titlePanel;
    }


    public JButton createAdminButton() {
        adminButton = new JButton();
        ImageIcon icon = new ImageIcon("./data/media/for admin.jpg");
        adminButton.setBounds(251, 326, 200, 80);
        adminButton.setText("For Admin");
        adminButton.setFocusable(false);
        adminButton.setIcon(icon);
        adminButton.setHorizontalTextPosition(JButton.CENTER);
        adminButton.setVerticalTextPosition(JButton.BOTTOM);
        adminButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        adminButton.setIconTextGap(-1);
        adminButton.setForeground(Color.black);
        adminButton.setBackground(Color.lightGray);
        adminButton.addActionListener(this);
        return adminButton;
    }

    public JButton createDonateButton() {
        donationButton = new JButton();
        ImageIcon icon = new ImageIcon("./data/media/donate logo.png");
        donationButton.setBounds(251, 476, 200, 80);
        donationButton.setText("To Donate");
        donationButton.setFocusable(false);
        donationButton.setIcon(icon);
        donationButton.setHorizontalTextPosition(JButton.CENTER);
        donationButton.setVerticalTextPosition(JButton.BOTTOM);
        donationButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        donationButton.setIconTextGap(-1);
        donationButton.setForeground(Color.black);
        donationButton.setBackground(Color.lightGray);
        donationButton.setVisible(true);
        donationButton.addActionListener(this);
        return donationButton;
    }

    public JButton createSaveButton() {
        saveButton = new JButton("Save to file");
        saveButton.setBounds(441, 720, 125, 80);
        saveButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        saveButton.setForeground(Color.black);
        saveButton.setBackground(Color.lightGray);
        saveButton.setVisible(true);
        saveButton.addActionListener(this);
        return saveButton;
    }

    public JButton createLoadButton() {
        loadButton = new JButton("Load from file");
        loadButton.setBounds(566, 720, 125, 80);
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        loadButton.setForeground(Color.black);
        loadButton.setBackground(Color.lightGray);
        loadButton.addActionListener(this);
        loadButton.setVisible(true);
        return loadButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(adminButton)) {
            setMainMenuInvisible();
            forAdmin.setVisible(true);
        }
        if (actionSource.equals(donationButton)) {
            setMainMenuInvisible();
            toDonate.setVisible(true);

        }
        if (actionSource.equals(saveButton)) {
            saveToFile();
        }
        if (actionSource.equals(loadButton)) {
            loadFromFile();
        }
    }

    public TrackDonationsRecords getTrackDonationsRecords() {
        return trackDonationsRecords;
    }

    public TrackDonorInfo getTrackDonorInfo() {
        return trackDonorInfo;
    }

    public TrackWildlifeInfo getTrackWildlifeInfo() {
        return trackWildlifeInfo;
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    public CreateDonorProfile getCp() {
        return cp;
    }

    public ForAdmin getForAdmin() {
        return forAdmin;
    }

    public ToDonate getToDonate() {
        return toDonate;
    }

    public AddWildlife getAw() {
        return aw;
    }

    public MakeDonations getMd() {
        return md;
    }

    public ConservationSite getCs() {
        return cs;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getDonationButton() {
        return donationButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    @Override
    public JButton createBackToPreviousButton() {
        return null;
    }

    // EFFECTS: saves the conservation site states to file, alert the user with a window message showing if
    //saving data to file is successful
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(cs);
            jsonWriter.close();
            String msg = "You have successfully saved " + cs.getName() + " to " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Save data", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(msg);
        } catch (FileNotFoundException e) {
            String msg = "Unable to write to file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "File Not Found Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(msg);
        }

    }


    // MODIFIES: this
    // EFFECTS: loads the conservation site from file, alert the user with a window message showing
    // if load file is successful
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void loadFromFile() {
        try {
            cs = jsonReader.read();
            String msg = "You have successfully loaded " + cs.getName() + " from " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Load data", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(msg);
        } catch (IOException e) {
            String msg = "Unable to read from file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Load File Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(msg);
        }
    }

}
