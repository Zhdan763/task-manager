package shared.util;

import server.command.CommandProcessor;
import shared.command.Command;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.ParseException;

public class ReadUTF {
    private static ReadUTF readUTF;

    private ReadUTF() {
    }

    public static synchronized ReadUTF getInstance() {
        if (readUTF == null) {
            readUTF = new ReadUTF();
        }
        return readUTF;
    }

    public void readUtfServer(DataInputStream inputStream, int idClient) throws IOException, ParseException {

        String json = inputStream.readUTF();
        Command command = JsonSerializer.getInstance().readValueFromText(json, Command.class);
        command.setClientId(idClient);
        CommandProcessor.getInstance().processCommand(command);
    }

    public void readUtfClient(DataInputStream inputStream) throws IOException {
        String json = inputStream.readUTF();
        Command command = JsonSerializer.getInstance().readValueFromText(json, Command.class);
        client.command.CommandProcessor.getInstance().processCommand(command);
    }


}
