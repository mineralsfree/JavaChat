package men.brakh;


import men.brakh.handler.Handler;
import men.brakh.handler.MessageHandler;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.CloseReason.CloseCodes;
import java.io.IOException;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@ServerEndpoint(value = "/chat")
public class    WebServer {
    public static Server server;
    public static Map<String, String> hashMap = new HashMap<String, String>();

    private Logger logger = Logger.getLogger(this.getClass().getName());
    @OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
    }
    @OnMessage
    public String onMessage(String message, Session session) {
        Message msg = Message.getMessage(message);
        if (!hashMap.containsKey(session.getId())){
            hashMap.put(session.getId(),msg.getUser().getName());
        }
        new Handler(session,msg,server);
        System.out.println(Message.getMessage(message).getString("web"));
        if (message.equals("quit")) {

            try {
                session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
        if (hashMap.containsKey(session.getId())){
            String name = hashMap.get(session.getId());
          //  Type uType = (server.customerQueue.getByUserID(3).getUserType(name));
              //      Message msg = new Message(new User(name, uType),"",MessageType.EXIT);
              //      new MessageHandler(msg,server,null);
        }
    }
}