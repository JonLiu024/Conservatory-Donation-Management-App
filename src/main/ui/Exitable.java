package ui;

import javax.swing.*;

public interface Exitable {
    //MODIFIES: this
    //EFFECTS: GoBackButton is instantiated; the position and features of the GoBackButton on the frame or panel
    //are set, the actionListener of the panel is added on GoBackButton, returns GoBackButton
    JButton createGoBackButton();
}
