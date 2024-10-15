package shared.exceptions;

public class PropertiesFileNotFoundException extends Exception {
    public PropertiesFileNotFoundException() {
        super();
    }

    public PropertiesFileNotFoundException(String message) {
        super(message);
    }
}
