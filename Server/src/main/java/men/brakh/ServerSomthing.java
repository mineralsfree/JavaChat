package men.brakh;



import java.io.*;
import java.net.Socket;

public class ServerSomthing extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public ServerSomthing (Socket socket) throws IOException {

        this.socket = socket;
        in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run(){
        String word;
        try {

            while (true) {
                    word = in.readLine();

                    System.out.print("recieved message");
                    Message msg = Message.getMessage(word);
                    if(word.equals("stop"))
                {  break;                }
                for (ServerSomthing vr : Server.serverList) {
                    vr.send(msg.getString());

                    // отослать принятое сообщение с
                    // привязанного клиента всем остальным включая его
                }
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


