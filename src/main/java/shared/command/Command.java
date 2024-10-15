package shared.command;

import java.io.Serializable;

public class Command implements Serializable {
    int id;
    Object data;
    int clientId;

    public Command() {
    }

    public Command(int id, Object data) {
        this.id = id;
        this.data = data;
    }

    public Command(int id, Object data, int clientId) {
        this.id = id;
        this.data = data;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", data=" + data +
                ", clientId=" + clientId +
                '}';
    }
}