package men.brakh.handler;

import men.brakh.Message;
import men.brakh.Sender.Sender;
import men.brakh.Sender.WebSender;
import men.brakh.Server;

import javax.websocket.Session;

public class Handler {
    public Session session;

   public Handler(Session session, Message msg, Server server){

       Sender sender = new WebSender(session);
       new MessageHandler(msg,server,sender);
      // server.logger.log("Someone has connected");
    }
}
