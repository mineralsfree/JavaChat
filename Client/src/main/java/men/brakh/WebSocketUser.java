package men.brakh;


import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
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
    private Session session;
    private Client client;
    private TextArea messageBox;


    public WebSocketUser(Client client, TextArea  messageBox ){
            this.client= client;
            this.messageBox = messageBox;


        try{
            WebSocketContainer container=ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8081/chat";
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

            messageBox.appendText(str + "\n");

        System.out.println(str);
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {

        }
    }
}
