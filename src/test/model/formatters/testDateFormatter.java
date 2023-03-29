package model.formatters;

import static org.junit.jupiter.api.Assertions.*;

import model.formatters.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class testDateFormatter {
    private LocalDate localDate;
    private String localDateString;

    @BeforeEach
    public void setup() {
        localDate = LocalDate.now();
        localDateString = LocalDate.now().toString();

    }

    @Test
    public void testToStringLocalDate() {
        assertEquals(localDateString, DateFormatter.toStringLocalDate(localDate));
        assertEquals("null", DateFormatter.toStringLocalDate(null));

    }

    @Test
    public void testStrToLocalDate() {
        assertEquals(localDate, DateFormatter.strToLocalDate(localDateString));
        assertNull(DateFormatter.strToLocalDate("null"));
    }



}
