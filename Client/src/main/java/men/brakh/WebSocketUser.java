package men.brakh;

import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint
public class WebSocketUser {
    private final String uri="ws://localhost:8081/chat";
    private Session session;
    private Client client;
    public WebSocketUser(Client client){
            this.client= client;
        try{
            WebSocketContainer container=ContainerProvider.
                    getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        }catch(Exception ex){

        }
    }

    @OnOpen
    public void onOpen(Session session){
        this.session=session;
    }

    @OnMessage
    public void onMessage(String message, Session session){
        Message msg = Message.getMessage(message);

        String str = msg.getString();
        if (msg.getMt().equals(MessageType.REG)){
            int id = Integer.parseInt(msg.getMessage());
            User usr = client.getUser();
            usr.setId(id);
            return;
        }
        if (msg.getMt().equals(MessageType.ID)){
           client.setChat(msg.getChatID());
            return;
        }

        System.out.println(str);
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {

        }
    }
}
