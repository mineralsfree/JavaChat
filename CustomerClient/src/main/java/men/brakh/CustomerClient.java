package men.brakh;

import java.io.IOException;

public class CustomerClient extends Client {
    final static String ip = "localhost";
    public static int port = 1488;
    public String username;
    public boolean isRegistred = false;
    public boolean Stopped = false;
    public boolean isSent = false;
    CustomerClient(String ip,int port) throws IOException {

        super(ip,port);
    }

    public void StringHandler(String msg){
        if (Stopped){
            registerUser(username);
            Stopped = false;

            SendServer(new Message(this.getUser(), "", MessageType.REG,getChat()).getJson());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SendServer(new Message(this.getUser(), msg, MessageType.OK,getChat()).getJson());
        } else {
            if (isRegistred && !isSent){
                SendServer((new Message(getUser(), "",MessageType.REG,getChat())).getJson());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isSent = true;
            }
            if ((msg.startsWith("/"))) {
                String[] msgarr = msg.split(" ");
                if ((msgarr[0].equals("/register") && (msgarr.length > 1))) {
                    registerUser(msgarr[1]);
                    username = msgarr[1];

                }
                if ((msgarr[0].equals("/leave")) && (isRegistred)) {
                    SendServer(new Message(this.getUser(), "", MessageType.LEAVE,getChat()).getJson());
                    Stopped = true;
                }
                if ((msgarr[0].equals("/exit")) && (isRegistred)) {

                    quit();
                    System.exit(0);
                }
            } else if (isRegistred) {
                SendServer(new Message(this.getUser(), msg, MessageType.OK,getChat()).getJson());
            } else {
                System.out.println("register to start chat");
                System.out.println("/register + name");
            }

        }

    }
    public void registerUser(String Username){
        setUser(new Customer(Username));
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
