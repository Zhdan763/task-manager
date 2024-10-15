package server.command;

import shared.util.ReadUTF;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.ParseException;

public class CommandListener extends Thread {
    private DataInputStream inputStream;
    private int idClient;

    public CommandListener(DataInputStream inputStream, int idClient) {
        this.inputStream = inputStream;
        this.idClient = idClient;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                if (inputStream.available() > 0) {

                    try {
                        ReadUTF.getInstance().readUtfServer(inputStream, idClient);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


