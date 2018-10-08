package men.brakh;





import men.brakh.Sender.ConsoleSender;
import men.brakh.Sender.Sender;
import men.brakh.handler.MessageHandler;

import java.io.*;
import java.net.Socket;


public class ServerSomthing extends Thread{
    private Socket socket;
    private Server server;
    private BufferedReader in;
    private BufferedWriter out;
    private Sender sender;
    public ServerSomthing (Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        sender = new ConsoleSender(out);

        start();
    }



    @Override
    public void run(){
        String word;
        try {

            while (true) {
                    word = in.readLine();
                    Message msg = Message.getMessage(word);
                new MessageHandler(msg,server,sender);
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


