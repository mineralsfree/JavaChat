import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Messanger {
	public  boolean CheckString(String userInput) {
		String trimmed = userInput.trim();
		int words =trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;
		if (words ==2) {
			return true;
		}
		return false;
	}
public static void main(String[] args) throws IOException {
	Messanger msngr = new Messanger();
	BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	String vline = keyboard.readLine();


	 String init = vline.substring(vline.indexOf(" ")+1);
	 if ((vline.startsWith("/register")) &&  msngr.CheckString(init)) 	 {
				 // give string without substring "/register"	
		 int port = 1488;	
		 try {
		 InetAddress adr = InetAddress.getByName("127.0.0.1");
		 Socket clientSocket = new Socket(adr, port);	 
         InputStream sin = clientSocket.getInputStream();
         OutputStream sout = clientSocket.getOutputStream();

         //  онвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщени€.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);
         String line =null;
         out.writeUTF(init); // отсылаем введенную строку текста серверу.
         out.flush(); // заставл€ем поток закончить передачу данных.		
         line = in.readUTF(); // ждем пока сервер отошлет строку текста.    
			 
			 
			
			 while (true) {
	                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
	                System.out.println("Sending this line to the server...");
	                out.writeUTF(line); // отсылаем введенную строку текста серверу.
	                out.flush(); // заставл€ем поток закончить передачу данных.
	                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
	                System.out.println("The server was very polite. It sent me this : " + line);
	                System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
	                System.out.println();
			 }
			 	     
	
		 
	 } catch (IOException e) {
         System.err.println(e);
     }
}
}
}

