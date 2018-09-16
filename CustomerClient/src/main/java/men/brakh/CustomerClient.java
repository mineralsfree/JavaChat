package men.brakh;

import java.io.IOException;

public class CustomerClient extends Client {
    final static String ip = "localhost";
    public static int port = 1488;
    public boolean isRegistred = false;
    CustomerClient(String ip,int port) throws IOException {

        super(ip,port);
    }

    public void StringHandler(String msg){

        if ((msg.startsWith("/"))&&(msg.length()!=0)){
            String[] msgarr = msg.split(" ");
            if ((msgarr[0].equals("/register") && (msgarr.length>1))){
                registerUser(msgarr[1]);

            }
            if (msgarr[0] == "/exit"){
                quit();
            }
        } else if(isRegistred) {
            SendServer(new Message(this.getUser(),msg, MessageType.OK).getJson());
        } else {
            System.out.println("register to start chat");
            System.out.println("/register + name");
        }



    }
    public void registerUser(String Username){
        setUser(new Customer(Username));
        SendServer((new Message(getUser(), "",MessageType.REG)).getJson());
        isRegistred = true;
    }

    public static void main(String[] args){

        try {
            new CustomerClient(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
