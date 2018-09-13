package men.brakh;

        import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.LinkedList;

public class Server {

    public static final int PORT = 1488;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<ServerSomthing>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента



    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1488);

        System.out.println("Server Started");
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket));
                    for (ServerSomthing sv : Server.serverList){
                        System.out.print(sv.getName());
                    }
                    // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
