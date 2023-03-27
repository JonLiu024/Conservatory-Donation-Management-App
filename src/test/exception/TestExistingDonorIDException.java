package exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CreateDonorProfile;
import ui.LaunchFundTrackerAppGUI;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestExistingDonorIDException {

    private ExistingDonorIDException exception;


    @Test
    public void testConstructor() {
        exception = new ExistingDonorIDException("a new exception");
        assertEquals("a new exception", exception.getMessage());
    }



}
