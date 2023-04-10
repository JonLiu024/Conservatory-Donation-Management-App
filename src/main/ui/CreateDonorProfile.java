package ui;

import exception.ExistingDonorIDException;
import model.Donor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Representing a JPanel components of the LauNchFundTrackerAppGUI for users to create a donor profile
public class CreateDonorProfile extends JPanel implements ActionListener, FormatDetectable, Resetable {
    private final FundTrackerAppGUI mainFrame;
    private JTextField textFieldDonorID;
    private JTextField textFieldEmail;
    private JButton submissionButton;

    //REQUIRES: mainFrame is not null
    //EFFECTS: creates a createDonorProfile with associated features (bounds, background colour, layout);
    //sets mainFrame and adds the associated Jcomponents
    //REFERENCE:
    public CreateDonorProfile(FundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createDonorIDTextField());
        this.add(createEmailTextField());
        this.add(createSubmissionButton());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the Jbutton for the user to submit their donor profile information
    public JButton createSubmissionButton() {
        submissionButton = new JButton("Submit");
        submissionButton.setBounds(490, 610, 100, 40);
        submissionButton.setFocusable(false);
        submissionButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        submissionButton.setBackground(Color.pink);
        submissionButton.addActionListener(this);
        return submissionButton;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns a Jtextfield for the user to enter the donor ID of their created profile
    public JTextField createDonorIDTextField() {
        JLabel label = new JLabel("Please create a donor ID: ");
        label.setBounds(125,125, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        textFieldDonorID = new JTextField();
        textFieldDonorID.setPreferredSize(new Dimension(300, 30));
        textFieldDonorID.setBounds(125, 150, 400, 40);
        return textFieldDonorID;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns a Jtextfield for the user to enter the email address of their created profile
    public JTextField createEmailTextField() {
        JLabel label = new JLabel("Please enter your email: ");
        label.setBounds(125,250, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        textFieldEmail = new JTextField();
        textFieldEmail.setPreferredSize(new Dimension(300, 30));
        textFieldEmail.setBounds(125, 276, 400, 40);
        return textFieldEmail;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(submissionButton)) {
            try {
                String donorID = textFieldDonorID.getText();
                String email = textFieldEmail.getText().toLowerCase();
                if (mainFrame.getCs().getDonorIDList().contains(donorID)) {
                    throw new ExistingDonorIDException("The donor ID existed, please create another one: ");
                }
                formatChecker(email);
                Donor donor = new Donor(donorID, email);
                mainFrame.getCs().addDonor(donor);
                clearContents();
                this.setVisible(false);
                mainFrame.getMd().setVisible(true);
            } catch (ExistingDonorIDException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            } catch (InputMismatchException exception) {
                JOptionPane.showMessageDialog(null, "Please enter correct email format: ",
                        "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }



    @Override
    public void formatChecker(String str) throws InputMismatchException {
        Pattern pattern = Pattern.compile("[0-9a-z]+(\\.[0-9a-z]+)*@[a-z0-9]+\\.(com|ca)");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new InputMismatchException("Please enter your email address in valid format: ");
        }
    }

    @Override
    public void clearContents() {
        this.textFieldEmail.setText("");
        this.textFieldDonorID.setText("");
    }
}
