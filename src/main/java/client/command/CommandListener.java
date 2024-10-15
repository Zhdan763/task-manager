package client.command;

import shared.util.ReadUTF;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandListener extends Thread {
    private Socket socket;
    private DataInputStream inputStream;


    public CommandListener(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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
                    ReadUTF.getInstance().readUtfClient(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
