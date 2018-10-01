package men.brakh;

import com.sun.deploy.security.ValidationState;

import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat")
public class WebServer {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println(Message.getMessage(message).getString());
        try {
            session.getBasicRemote().sendText(new Message(new User("vasya"),"lol",MessageType.OK).getJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (message.equals("quit")) {

            try {
                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}