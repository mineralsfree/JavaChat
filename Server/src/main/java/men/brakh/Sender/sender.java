package men.brakh.Sender;

import men.brakh.Message;

public interface sender {
    void send(String msg);
    default void send(Message msg) {
        send(msg.getJson());
    }
}
