package men.brakh.Sender;

import men.brakh.Message;

public interface Sender {
    void send(String msg);
    default void send(Message msg) {
        send(msg.getJson());
    }
    default void ServerSend(String msg){
        send(msg);
    }
}
