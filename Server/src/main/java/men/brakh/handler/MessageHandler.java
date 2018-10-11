package men.brakh.handler;

import men.brakh.*;
import men.brakh.Sender.Sender;

public class MessageHandler {
    public  MessageHandler(Message msg, Server server, Sender sender) {
        switch (msg.getMt()) {
            case OK:
                if (msg.getUser().getType() == Type.AGENT) {
                    if (isConnected(msg.getUser(),server)) {
                        server.customerQueue.getByAgentName(msg.getUser().getName()).addMessage(msg);
                        Sender targetSender = server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomerSS();
                        targetSender.send(msg.getString(targetSender.toString())); //
                        server.logger.log(msg.getString(sender.toString()));
                    }

                } else {
                    server.customerQueue.getByUserName(msg.getUser().getName()).addMessage(msg);
                    if (isConnected(msg.getUser(),server)) {
                        Sender targetSender = server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS();
                        targetSender.send(msg.getString(targetSender.toString()));
                        server.logger.log(msg.getString(sender.toString()));
                    } else {

                    }
                }
                break;
            case REG:
                SocketUser socketUser = new SocketUser(msg.getUser(), sender);
                if (msg.getUser().getType() == Type.AGENT) {
                    server.agentQueue.AddAgent(socketUser);
                    server.logger.log("Agent " + msg.getUser().getName() + " registered in System");
                } else {
                    server.customerQueue.AddUser(msg.getUser(), sender);
                    server.logger.log("Customer " + msg.getUser().getName() + " registered in System");
                }

                break;
            case EXIT:
                if (msg.getUser().getType() == Type.AGENT) {
                    User usr = server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().GetUser();
                    Sender targetSender =server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomerSS(); // Customer Sender
                    targetSender.ServerSend(msg.getString());
                    server.customerQueue.AddUser(usr, server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().GetSender());
                    server.logger.log("Agent " + msg.getUser().getName() + " closed Application");
                } else {
                    SocketUser usr = server.customerQueue.getByUserName(msg.getUser().getName()).getAgent();
                    Sender targetSender =  server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS();
                    targetSender.ServerSend(msg.getString());
                    server.agentQueue.AddAgent(usr);
                    server.logger.log("Customer " + msg.getUser().getName() + " closed Application");
                }

                break;
            case LEAVE:
                SocketUser usr = server.customerQueue.getByUserName(msg.getUser().getName()).getAgent();
                server.agentQueue.AddAgent(usr);
                Sender targetSender =   server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS();
                targetSender.ServerSend("User " + msg.getUser().getName() + " Left the chat");
                server.customerQueue.DeleteChat(server.customerQueue.getByUserName(msg.getUser().getName()));
                server.logger.log("User " + msg.getUser().getName() + " Left the chat");


                break;
        }

    }
    public boolean isConnected (User user,Server server){ //checks if there is a connection between user and agent
        switch (user.getType()) {
            case AGENT:
                if (server.customerQueue.getByAgentName(user.getName()) != null) return true;
                break;
            case CUSTOMER:
                if (server.customerQueue.getByUserName(user.getName()).isAgentHere()) return true;
                break;

        }
        return false;
    }
}
