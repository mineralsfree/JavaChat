package men.brakh;





import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;


public class ServerSomthing extends Thread{
    private Socket socket;
    private Server server;
    private BufferedReader in;
    private BufferedWriter out;
    public ServerSomthing (Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    public void MessageHandler(Message msg){
switch (msg.getMt()){
    case OK:

        if (msg.getUser().getType() == Type.AGENT){
            if(isConnected(msg.getUser())){
                server.customerQueue.getByAgentName(msg.getUser().getName()).addMessage(msg);
                server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomerSS().send(msg.getString());
                server.logger.log(msg.getString());
            }

        }

        else{
            server.customerQueue.getByUserName(msg.getUser().getName()).addMessage(msg);
            if(isConnected(msg.getUser())){
                server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS().send(msg.getString());
                server.logger.log(msg.getString());
            } else {

            }
        }
        break;
    case REG:
        SocketUser socketUser = new SocketUser(msg.getUser(),this);
        if (msg.getUser().getType() == Type.AGENT){
            server.agentQueue.AddAgent(socketUser);
            server.logger.log("Agent "+ msg.getUser().getName()+ " registred in System" );
        }
        else{
            server.customerQueue.AddUser(msg.getUser(),this);
            server.logger.log("Customer "+ msg.getUser().getName()+ " registred in System" );
        }

        break;
    case EXIT:
        if (msg.getUser().getType() == Type.AGENT){
            User usr = server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().GetUser();
            server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomerSS().send(msg.getString());
            server.customerQueue.AddUser(usr,  server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().GetServerSomthing());
            server.logger.log("Agent "+ msg.getUser().getName()+ " closed Application" );
        }else{
            SocketUser usr = server.customerQueue.getByUserName(msg.getUser().getName()).getAgent();
            server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS().send(msg.getString());
            server.agentQueue.AddAgent(usr);
            server.logger.log("Customer "+ msg.getUser().getName()+ " closed Application" );
        }

        break;
    case LEAVE:
        SocketUser usr = server.customerQueue.getByUserName(msg.getUser().getName()).getAgent();
        server.agentQueue.AddAgent(usr);
        server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS().send(msg.getString());
        server.customerQueue.DeleteChat(server.customerQueue.getByUserName(msg.getUser().getName()));
        server.logger.log("User "+ msg.getUser().getName()+ " Left the chat" );



        break;
}

    }
    public boolean isConnected(User user){ //checks if there is a connection between user anf agent
        switch (user.getType()){
            case AGENT:
                if (server.customerQueue.getByAgentName(user.getName())!=null) return true;
                break;
            case CUSTOMER:
                if (server.customerQueue.getByUserName(user.getName()).isAgentHere()) return true;
                break;

        }
        return false;
    }
    @Override
    public void run(){
        String word;
        try {

            while (true) {
                    word = in.readLine();
                    Message msg = Message.getMessage(word);
                this.MessageHandler(msg);
            }

               } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public  void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

}


