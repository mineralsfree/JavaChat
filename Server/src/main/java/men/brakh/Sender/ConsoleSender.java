package men.brakh.Sender;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConsoleSender implements sender {
    private BufferedWriter out;
    ConsoleSender(BufferedWriter out){
        this.out = out;

    }
    @Override
    public void send(String msg) {
        try {
            out.write(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
