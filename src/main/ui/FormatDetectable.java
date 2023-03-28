package ui;

public interface FormatDetectable {

    //EFFECTS: check the format of the str, throws a MisInputException if str does not satisfy date or email format
    void formatChecker(String str);
}
