package men.brakh;


import java.io.IOException;

public class AgentClient extends Client{
    final static String ip = "localhost";
    public static int port = 1488;
    public boolean isRegistred = false;
    AgentClient(String ip,int port) throws IOException {
            super(ip,port);
    }

    public void StringHandler(String msg){

        if ((msg.startsWith("/"))&&(msg.length()!=0)){
            String[] msgarr = msg.split(" ");
            if ((msgarr[0].equals("/register") && (msgarr.length>1))){
                registerUser(msgarr[1]);
                isRegistred=true;
            }
            if (msgarr[0].equals("/exit")){
                quit();
                System.exit(0);
            }
        } else if(isRegistred) {
            SendServer(new Message(this.getUser(),msg, MessageType.OK,getChat()).getJson());

        } else{
            System.out.println("register to start chat");
            System.out.println("/register + name");
        }



    }
    public void registerUser(String Username){
        setUser(new Agent(Username));
        SendServer((new Message(getUser(), "",MessageType.REG,getChat())).getJson());

    }

    public static void main(String[] args){

        try {
            new AgentClient(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}