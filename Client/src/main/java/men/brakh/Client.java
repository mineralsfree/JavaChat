package men.brakh;


import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public abstract class Client{
    private User user;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private WebSocketUser webSocketUser;
    private ReadMsg readThread;
    private WriteMsg writeThread;
    private int chat = -1;



    public Client(TextArea messageBox){
  //      try {
//           Socket clientSocket = new Socket(ip, port);
            webSocketUser = new WebSocketUser(this,messageBox);


    //    inputUser = new BufferedReader(new InputStreamReader(System.in));
        // читать соообщения с сервера
    //    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // писать туда же
        //out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
           // readThread= new ReadMsg();
         //   writeThread = new WriteMsg();
//        } catch (IOException e) {
 //           e.printStackTrace();
 //       }
    /*    try {
            readThread.join();
          //  writeThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }*/
    }
    public User getUser(){
        return user;
    }

    private class ReadMsg extends Thread {
        private Boolean isKilled;
        public ReadMsg() {
            isKilled = false;
            this.start();
        }
        public void kill() {
            isKilled = true;
            this.interrupt();
        }
        @Override
        public void run() {

            String str;
            while (!isKilled) {
              //  try {
                  //  str = in.readLine(); // ждем сообщения с сервера
                                        // здесь может быть десериализация

                //    System.out.println("lol");
            //    } catch (IOException e) {
             //       e.printStackTrace();
              //  }

                //checkServerResponse(str);
            }
        }
    }

    public abstract void StringHandler(String msg);
    public class WriteMsg extends Thread {

    public WriteMsg(){
        this.start();
    }
        @Override
        public void run() {

            while (true) {
                String answer;
                Scanner scan = new Scanner(System.in);
                answer = scan.nextLine();
  //                  StringHandler(answer);

            }
        }
    }
    public void SendServer(String msg){
    //    try {
            webSocketUser.sendMessage(msg);
      //      out.write (msg  + "\n");

     //       out.flush();
   //     } catch (IOException e) {
    //        e.printStackTrace();
    //    }

    }
    public void setUser(User user){
        this.user = user;
    }

    public void quit(){
        SendServer((new Message(user,(" left the conversation!"),MessageType.EXIT,this.chat)).getJson());
        System.out.println("Reboot Application to enter it");

    }

    public WebSocketUser getWebSocketUser() {
        return webSocketUser;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public int getChat() {
        return chat;
    }
}

