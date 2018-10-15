package men.brakh.handler;

import men.brakh.*;
import men.brakh.Sender.Sender;

public class MessageHandler {
    public  MessageHandler(Message msg, Server server, Sender sender) {
        int chatid = msg.getChatID();
        switch (msg.getMt()) {
            case OK:
                if (msg.getUser().getType() == Type.AGENT) {
                    if (isConnected(msg.getUser(),server,chatid)) {

                        server.customerQueue.getByID(msg.getUser().getId()).addMessage(msg);

                        Sender targetSender = server.customerQueue.getByID(msg.getUser().getId()).getCustomerSS();
                        targetSender.send(msg.getString(targetSender.toString())); //Костыли + велосипед

                        server.logger.log(msg.getString());
                    }

                } else {
                    server.customerQueue.getByID(msg.getUser().getId()).addMessage(msg);
                    if (isConnected(msg.getUser(),server,chatid)) {
                        Sender targetSender = server.customerQueue.getByID(msg.getUser().getId()).getAgentSS();
                        targetSender.send(msg.getString(targetSender.toString()));

                    } else {
                        server.logger.log(msg.getString());
                    }
                }
                break;
            case REG:
                int id = server.getNewId();
                User user = msg.getUser();
                user.setId(id);
                SocketUser socketUser = new SocketUser(user, sender);
                if (msg.getUser().getType() == Type.AGENT) {
                    server.agentQueue.AddAgent(socketUser);

                    sender.ServerReg(String.valueOf(id));
                    server.logger.log("Agent " + user.getName() + " registered in System");
                } else {
                    server.customerQueue.AddUser(user, sender);
                    int Chatid = server.customerQueue.getByUserName(msg.getUser().getName()).getId();
                    sender.ServerReg(String.valueOf(id));
                    sender.ServerRegID(String.valueOf(Chatid));
                    server.logger.log("Customer " + user.getName() + " registered in System");
                }
                server.checkFreeAgents();
                break;
            case EXIT:
                if (msg.getUser().getType() == Type.AGENT) {
                    User usr = server.customerQueue.getByID(msg.getUser().getId()).getCustomer().GetUser();
                    Sender targetSender =server.customerQueue.getByID(msg.getUser().getId()).getCustomerSS(); // Customer Sender
                    targetSender.ServerSend(msg.getString());
                    server.customerQueue.AddUser(usr, server.customerQueue.getByID(msg.getUser().getId()).getCustomer().GetSender());
                    server.logger.log("Agent " + msg.getUser().getName() + " closed Application");
                } else {
                    SocketUser usr = server.customerQueue.getByID(msg.getUser().getId()).getAgent();
                    Sender targetSender =  server.customerQueue.getByID(msg.getUser().getId()).getAgentSS();
                    targetSender.ServerSend(msg.getString());
                    server.agentQueue.AddAgent(usr);
                    server.logger.log("Customer " + msg.getUser().getName() + " closed Application");
                }
                server.checkFreeAgents();
                break;
            case LEAVE:
                SocketUser usr = server.customerQueue.getByID(msg.getUser().getId()).getAgent();
                server.agentQueue.AddAgent(usr);
                Sender targetSender =   server.customerQueue.getByID(msg.getUser().getId()).getAgentSS();
                targetSender.ServerSend("User " + msg.getUser().getName() + " Left the chat");
                server.customerQueue.DeleteChat(server.customerQueue.getByID(msg.getUser().getId()));
                server.logger.log("User " + msg.getUser().getName() + " Left the chat");
                server.checkFreeAgents();

                break;
        }

    }
    public boolean isConnected (User user,Server server,int chatid){ //checks if there is a connection between user and agent

        switch (user.getType()) {
            case AGENT:
                if (server.customerQueue.getByID(user.getId()) != null) return true;
                break;
            case CUSTOMER:
                if (server.customerQueue.getByID(user.getId()).isAgentHere()) return true;
                break;

        }
        return false;
    }
}
