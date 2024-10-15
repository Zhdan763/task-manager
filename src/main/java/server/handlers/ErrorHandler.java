package server.handlers;

import shared.command.Command;

import java.text.ParseException;

public class ErrorHandler implements Handler{
    @Override
    public void handle(Command command) {
        System.out.println("hello");
    }
}
