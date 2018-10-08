package men.brakh.Listener;

import men.brakh.Server;
import men.brakh.ServerSomthing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread{
    ServerSocket serverSocket;
    private int port;
    Server server;
    public SocketListener(int port,Server server){
        this.port = port;
        this.server = server;
    start();
    }

    @Override
    public void run(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            while (true) {


                Socket socket = serverSocket.accept();
                try {
                    server.serverList.add(new ServerSomthing(socket, server));
                } catch (IOException e) {

                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    }

