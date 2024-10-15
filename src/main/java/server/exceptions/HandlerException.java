package server.exceptions;

import java.io.IOException;

public class HandlerException extends IOException {

    public HandlerException() {
        super();
    }

    public HandlerException(String message) {
        super(message);
    }
}
