package men.brakh.Sender;

import men.brakh.Message;

public interface Sender {
    void send(String msg);
    default void send(Message msg) {
        send(msg.getJson());
    }
    default void ServerSend(String msg,int chatid){
        send(msg);
    }
    default void ServerReg(String msg){
        send(msg);
    }
    default void ServerRegID(String msg){
        send(msg);
    }

}
