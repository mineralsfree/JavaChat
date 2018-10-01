package men.brakh.Sender;

import javax.websocket.Session;
import java.io.IOException;

public class WebSender implements sender{
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
}
