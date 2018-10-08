package men.brakh.Sender;

import men.brakh.Message;
import men.brakh.MessageType;
import men.brakh.User;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConsoleSender implements Sender {
    private BufferedWriter out;
   public ConsoleSender(BufferedWriter out){
        this.out = out;

    }
    @Override
    public void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ServerSend(String msg){
           send(msg);
    }
}
