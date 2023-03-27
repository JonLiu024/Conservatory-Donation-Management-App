package exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CreateDonorProfile;
import ui.LaunchFundTrackerAppGUI;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestExistingDonorIDException {

    private ExistingDonorIDException e;


    @Test
    public void testConstructor() {
        e = new ExistingDonorIDException("a new exception");
        assertEquals("a new exception", e.getMessage());
    }



}
