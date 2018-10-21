package men.brakh.Sender;

import men.brakh.Message;
import men.brakh.MessageType;
import men.brakh.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class APISender implements Sender {
    List<Message> messages = new LinkedList<>();

    @Override
    public void send(String msg) {
       // Message message = new Message(new User("server"),msg, MessageType.REG);
        messages.add(Message.getMessage(msg));
    }
    @Override
    public void ServerReg(String msg){
            Message message = new Message(new User("server"),msg, MessageType.REG);
        messages.add(message);

    }
    @Override
    public void send(Message msg) {
        messages.add(msg);
    }
    public Message returnMessage(){
        return messages.get(0);

    }
    public void ServerRegID(String msg){
        int id = Integer.parseInt(msg);
        Message message = new Message(new User("server"),"", MessageType.ID,id);
        messages.add(message);
    }

}
