import java.net.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import java.io.*;

public class Server {
	public static final int PORT = 1488;
	public enum Type {client, agent}
	  private ArrayDeque<Agent> agentList = new ArrayDeque<Agent>();
	  private ArrayDeque<User> userqueue = new ArrayDeque<User>();	  
	Server(){	
		
	}
	
	public void addToAgentList(Agent agent) {
	agentList.addLast(agent);
		
	}
	
	public void addToClientList(User user) {
		userqueue.addLast(user);
		
	}
	public Socket getListeningSocket(String usrAdress) {

			try {
		InetAddress iaddres = InetAddress.getByName(usrAdress);			
			Socket s =new Socket(iaddres,PORT);
			return s;
			} catch(IOException e) {		
			}
		
		return null;
	}
	public void registerUser(String UserInput) {  											 
		Scanner sc = new Scanner(UserInput);
		sc.useDelimiter(" ");
		User.Type type; 
			String sType = sc.next();
			String sName = sc.next();
			if (sType.equals("agent")) {
				type = User.Type.agent;
				Agent user = new Agent(sName,type,LocalDateTime.now());
				this.addToAgentList(user);	
			} else 
			if (sType.equals("client")){ 
				type = User.Type.client;
				Client user = new Client(sName,type,LocalDateTime.now());
							
				this.addToClientList(user);
			}
			
		}		
	public String getAgentName() {
		Agent agent = this.agentList.getFirst();
		return agent.getName();
	}
	public LocalDateTime getAgentRegTime() {
		Agent agent = this.agentList.getFirst();
		return agent.getRegTime();
	}	
	
	
	 
	public static void main(String[] ar)    {		 

		 Server server = new Server();

		 if (!server.userqueue.isEmpty() && !server.agentList.isEmpty()) {

			 
		 }	 		 
	      // случайный порт (может быть любое число от 1025 до 65535)
	       try {
	         ServerSocket ss = new ServerSocket(PORT); // создаем сокет сервера и привязываем его к вышеуказанному порту
	         System.out.println("Waiting for a client...");

	         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
	         
	         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
	         System.out.println();
	         
	         InputStream sin = socket.getInputStream();
	         OutputStream sout = socket.getOutputStream();

	 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
	         DataInputStream in = new DataInputStream(sin);
	         DataOutputStream out = new DataOutputStream(sout);

         String line = null;

         
         while(true) {            
           line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
           if (server.agentList.isEmpty()) {
           	
         		 server.registerUser(line);
         	 }
           
           System.out.println("The dumb client just sent me this line : " + line);
           System.out.println("I'm sending it back...");
           out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
           out.flush(); // заставляем поток закончить передачу данных.
           System.out.println("Waiting for the next line...");
           System.out.println();
           if (!server.agentList.isEmpty()) {
        	   System.out.println(server.getAgentName());
        	   System.out.println(server.getAgentRegTime());
           }
         }
      } catch(Exception x) { x.printStackTrace(); }
   }
}