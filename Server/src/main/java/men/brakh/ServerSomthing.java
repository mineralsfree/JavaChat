package men.brakh;





import java.io.*;
import java.net.Socket;



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
            if(isConnected(msg.getUser())){
                server.customerQueue.getByUserName(msg.getUser().getName()).addMessage(msg);
                server.customerQueue.getByUserName(msg.getUser().getName()).getAgentSS().send(msg.getString());
                server.logger.log(msg.getString());
            }
        }
        break;
    case REG:
        SocketUser socketUser = new SocketUser(msg.getUser(),this);
        if (msg.getUser().getType() == Type.AGENT){
            server.agentQueue.AddAgent(socketUser);

        }
        else{
            server.customerQueue.AddUser(msg.getUser(),this);
        }
        break;
    case EXIT:
        if (msg.getUser().getType() == Type.AGENT){
            User usr = server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().GetUser();
            server.customerQueue.AddUser(usr,  server.customerQueue.getByAgentName(msg.getUser().getName()).getCustomer().getSv());
        }else{
            SocketUser usr = server.customerQueue.getByUserName(msg.getUser().getName()).getAgent();
            server.agentQueue.AddAgent(usr);

        }



        break;
}

    }
    public boolean isConnected(User user){
        if ((server.customerQueue.getByAgentName(user.getName())!=null)
            || (server.customerQueue.getByUserName(user.getName())!=null))
            return true;
            else return false;
    }
    @Override
    public void run(){
        String word;
        try {

            while (true) {
                    word = in.readLine();
                   Message msg = Message.getMessage(word);


                    if(word.equals("stop"))
                {  break;                }

                this.MessageHandler(msg);


            }

               } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }

}


