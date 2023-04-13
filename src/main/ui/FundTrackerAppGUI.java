package ui;

import model.Conservatory;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Representing the main frame of the app
public class FundTrackerAppGUI extends JFrame implements ActionListener {

    private JPanel titlePanel;  //title section of the app
    private ForAdmin forAdmin;  //the page for admins
    private ToDonate toDonate;  //the page for donors
    private AddWildlife aw;     //the page to add a wildlife
    private MakeDonations md;    //the page to make donations
    private CreateDonorProfile cp; //the page to create donor profile
    private TrackDonationsRecords trackDonationsRecords; //the page to track donations records
    private TrackDonorInfo trackDonorInfo; //the page to track donor information
    private TrackWildlifeInfo twlInfo;     //the page to track wildlife information
    private JLabel backgroundLabel;        //the section that contains background image
    private JButton adminButton;            //the button to navigate to the admin page
    private JButton donationButton;         //the button to navigate to the donor page
    private JButton saveButton;             //the button to save the state of app to file
    private JButton loadButton;             //the button to load data from the file


    private static final String JSON_STORE = "./data/conservatory.json"; //sources file's pathname
    private Conservatory cs; //our conservation site
    private JsonWriter jsonWriter; //JSonWriter to write to file
    private JsonReader jsonReader; //JSonReader to read from file


    //MODIFIES: this
    //EFFECT: instantiates a conservation site, a jsonWriter, and a jsonReader to save or load data to/from file
    private void modelsInitiator() {
        cs = new Conservatory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    //MODIFIES: this
    //EFFECTS: adds the component buttons and labels to this,
    private void mainMenuPanelInitiator() {
        this.add(createTitlePanel());
        this.add(createDonateButton());
        this.add(createAdminButton());
        this.add(createSaveButton());
        this.add(createLoadButton());
        this.add(createBackgroundLabel());
    }


    //MODIFIES: this
    //EFFECTS: sets the associated features of the background label
    private JLabel createBackgroundLabel() {
        backgroundLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./data/media/background2.jpg");
        backgroundLabel.setIcon(imageIcon);
        backgroundLabel.setBounds(1, 200, 700, 500);
        return backgroundLabel;
    }


    //MODIFIES: this
    //EFFECT: instantiates the associated JComponents of this
    private void panelsInitiator() {
        aw = new AddWildlife(this);
        forAdmin = new ForAdmin(this);
        toDonate = new ToDonate(this);
        md = new MakeDonations(this);
        cp = new CreateDonorProfile(this);
        trackDonationsRecords = new TrackDonationsRecords(this);
        trackDonorInfo = new TrackDonorInfo(this);
        twlInfo = new TrackWildlifeInfo(this);
    }


    //MODIFIES: this
    //EFFECTS: adds the component labels to this
    private void addPanelsToFrame() {
        this.add(forAdmin);
        this.add(toDonate);
        this.add(aw);
        this.add(md);
        this.add(cp);
        this.add(trackDonationsRecords);
        this.add(trackDonorInfo);
        this.add(twlInfo);
    }


    //MODIFIES: this
    //EFFECTS: sets the Main menu components invisible
    private void setMainMenuInvisible() {
        adminButton.setVisible(false);
        saveButton.setVisible(false);
        loadButton.setVisible(false);
        donationButton.setVisible(false);
        backgroundLabel.setVisible(false);
    }


    //EFFECTS: create a LaunchFundTrackerAppGUI objects, instantiates and adds all the JComponents
    //, sets the features of the JFrame
    public FundTrackerAppGUI() {
        modelsInitiator();
        panelsInitiator();
        mainMenuPanelInitiator();
        addPanelsToFrame();
        windowEventHandler();
        this.setTitle("Wildlife Conservatory ");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        this.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: sets the titlePanel with associated features (photos, background colour and bounds) and returns it
    public JPanel createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 700, 140);
        titlePanel.setBackground(new Color(204, 255, 255));
        JLabel label1 = new JLabel();
        ImageIcon titleImage = new ImageIcon("./data/media/logoWildlifeConservatory250.jpg");
        String labelContent1 = "<html>Welcome to Tong's wildlife conservatory!<BR><BR></html>";
        label1.setText(labelContent1);
        label1.setIcon(titleImage);
        label1.setLayout(null);
        label1.setFont(new Font("Serif", Font.BOLD, 30));
        label1.setVerticalAlignment(1);
        titlePanel.add(label1);
        return titlePanel;
    }


    //MODIFIES: this
    //EFFECTS: sets and returns the button to show the admin menu
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


    //MODIFIES: this
    //EFFECTS: sets and returns the button to show to donate menu
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



    //MODIFIES: this
    //EFFECTS: sets and returns the button to save the state of the conservatory to file
    public JButton createSaveButton() {
        saveButton = new JButton("Save to file");
        saveButton.setBounds(421, 720, 135, 80);
        saveButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        saveButton.setForeground(Color.black);
        saveButton.setBackground(Color.lightGray);
        saveButton.setVisible(true);
        saveButton.addActionListener(this);
        return saveButton;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the button to load the status of the conservatory from file
    public JButton createLoadButton() {
        loadButton = new JButton("Load from file");
        loadButton.setBounds(556, 720, 135, 80);
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


    //Getters
    public TrackDonationsRecords getTrackDonationsRecords() {
        return trackDonationsRecords;
    }

    public TrackDonorInfo getTrackDonorInfo() {
        return trackDonorInfo;
    }

    public TrackWildlifeInfo getTwlInfo() {
        return twlInfo;
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

    public Conservatory getCs() {
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


    // EFFECTS: saves the conservation site states to file, alert the user with a window message showing whether or not
    //saving data to file is successful
    //REFERENCE: JsonSerializationDemo (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    private void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(cs);
            jsonWriter.close();
            String msg = "You have successfully saved " + cs.getName() + " to " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Save data", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            String msg = "Unable to write to file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "File Not Found Error", JOptionPane.ERROR_MESSAGE);
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
        } catch (IOException e) {
            String msg = "Unable to read from file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Load File Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    //MODIFIES: this
    //EFFECTS: adds a WindowAdapter object into the list of observers of this, windowClosing methods of observer
    //windowAdaptor is overriden and implemented
    //pops up a message window showing "Thanks for using our application", prints out the key events during the usage
    //REFERENCE: https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close
    private void windowEventHandler() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane.showMessageDialog(null, "Thanks for using our application");
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event);
                    }
                }
        });
    }


}
