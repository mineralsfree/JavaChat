package men.brakh.Sender;

import men.brakh.Message;
import men.brakh.MessageType;
import men.brakh.User;

import javax.websocket.Session;
import java.io.IOException;

public class WebSender implements Sender{
    private Session session;
    public WebSender(Session session){
        this.session = session;
    }
    @Override
    public void send(String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ServerSend(String msg){
        Message message = new Message(new User("server"),msg, MessageType.SERVER);
        try {
            session.getBasicRemote().sendText(message.getJson());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Session getSession() {
        return session;
    }
}
