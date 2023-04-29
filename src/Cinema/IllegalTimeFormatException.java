package Cinema;

public class IllegalTimeFormatException extends Exception {

    private static final long serialVersionUID = 7568427077693695817L;
    static final String message = "Input time must be within 0...24 for hours and 0..60 for minutes!";

    public IllegalTimeFormatException() {
        super(message);
    }
}
