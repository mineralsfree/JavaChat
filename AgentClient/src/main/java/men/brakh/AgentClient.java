package men.brakh;


import java.io.IOException;

public class AgentClient extends Client{
    final static String ip = "localhost";
    public static int port = 1488;
    AgentClient(String ip,int port) throws IOException {

            super(ip,port);
    }

    public void StringHandler(String msg){
        if ((msg.startsWith("/"))&&(msg.length()!=0)){
            String[] msgarr = msg.split(" ");
            if ((msgarr[0] == "/register")&& (msgarr.length>2)&& (msgarr[1] == "agent")){
                registerUser(msgarr[2]);
            }
            if (msgarr[0] == "/exit"){
                quit();
            }
        } else {
            SendServer(new Message(this.getUser(),msg, MessageType.OK).getJson());


        }



    }
    public void registerUser(String Username){
        setUser(new Agent(Username));
        SendServer((new Message(getUser(), "",MessageType.REG)).getJson());

    }
    public static void main(String[] args){

        try {
            new AgentClient(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}