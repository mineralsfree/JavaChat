package men.brakh.Listener;

import men.brakh.Server;
import men.brakh.WebServer;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebSocketListener extends Thread {



    public  WebSocketListener(Server nserver) {
        WebServer.server = nserver;
        start();
    }
        @Override
        public void run(){
        org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 8081, "", WebServer.class);
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
