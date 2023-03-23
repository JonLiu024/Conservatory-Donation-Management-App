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
import java.util.Scanner;

public class LaunchFundTrackerAppGUI extends JFrame implements ActionListener, CommonComponents {

    private JPanel titlePanel;
    private JPanel adminPanel;
    private JPanel donorPanel;
    private JButton adminButton;
    private JButton donationButton;
    private JButton saveButton;
    private JButton loadButton;

    private static final String JSON_STORE = "./data/conservatory.json"; //sources file's pathname
    private ConservationSite conservationSite; //our conservation site
    private JsonWriter jsonWriter; //JSonWriter to write to file
    private JsonReader jsonReader; //JSonReader to read from file



    //MODIFIES: this
    //EFFECT: creates a conservation site, a jsonWriter, and a jsonReader to save or load data to/from file
    private void init() {
        conservationSite = new ConservationSite();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }



    public LaunchFundTrackerAppGUI() {
        init();
        this.setTitle("Wildlife Conservatory ");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(createTitlePanel());
        this.add(createAdminButton());
        this.add(createDonateButton());
        this.add(createSaveButton());
        this.add(createLoadButton());
        this.add(createAdminPanel());
        this.add(createDonatePanel());;
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        Border border = BorderFactory.createLineBorder(new Color(255, 215, 0));
        this.setVisible(true);
    }


    public JPanel createAdminPanel() {
        JLabel label2 = new JLabel();
        adminPanel = new JPanel();
        adminPanel.setBounds(0, 141, 700, 350);
        adminPanel.setBackground(new Color(204, 255, 255));
        ImageIcon adminImage = new ImageIcon("./data/media/Admin.jpg");
        label2.setIcon(adminImage);
        adminPanel.add(label2);
        return adminPanel;
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



    public JPanel createDonatePanel() {
        donorPanel = new JPanel();
        donorPanel.setBounds(0, 491, 700, 350);
        donorPanel.setBackground(new Color(204, 255, 255));
        JLabel label3 = new JLabel();
        ImageIcon donateImage = new ImageIcon("./data/media/Donate.jpg");
        label3.setIcon(donateImage);
        donorPanel.add(label3);
        return donorPanel;
    }


    public JButton createAdminButton() {
        adminButton = new JButton();
        ImageIcon icon = new ImageIcon("./data/media/for admin.jpg");
        adminButton.setBounds(276, 276, 150, 80);
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
        donationButton.setBounds(276, 626, 150, 80);
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
        saveButton = new JButton();
        saveButton.setBounds(326, 741, 125, 100);
        saveButton.setText("Save to file");
        saveButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        saveButton.setVerticalTextPosition(JButton.CENTER);
        saveButton.setHorizontalTextPosition(JButton.CENTER);
        saveButton.setForeground(Color.black);
        saveButton.setBackground(Color.lightGray);
        saveButton.setVisible(true);
        saveButton.addActionListener(this);
        return saveButton;
    }

    public JButton createLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(451, 741, 125, 100);
        loadButton.setText("Load from file");
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        loadButton.setHorizontalTextPosition(JButton.CENTER);
        loadButton.setVerticalTextPosition(JButton.CENTER);
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
            setVisible(false);
            new ForAdmin();
        }
        if (actionSource.equals(donationButton)) {
            new ToDonate();
        }
        if (actionSource.equals(saveButton)) {
            saveToFile();
        }
        if (actionSource.equals(loadButton)) {
            loadFromFile();
        }

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
            jsonWriter.write(conservationSite);
            jsonWriter.close();
            String msg = "You have successfully saved " + conservationSite.getName() + " to " + JSON_STORE;
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
            conservationSite = jsonReader.read();
            String msg = "You have successfully loaded " + conservationSite.getName() + " from " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Load data", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(msg);
        } catch (IOException e) {
            String msg = "Unable to read from file: " + JSON_STORE;
            JOptionPane.showMessageDialog(null, msg, "Load File Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(msg);
        }
    }

}
