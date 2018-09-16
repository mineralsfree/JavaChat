package men.brakh;

        import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.LinkedList;
        import java.util.Timer;
        import java.util.TimerTask;


public class Server {

    public static final int PORT = 1488;
    public AgentQueue agentQueue;
    public CustomerQueue customerQueue;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<ServerSomthing>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента
    class ServerHandler extends Thread{
        public ServerHandler(){
            this.start();
        }
        @Override
        public void  run(){
            Timer timer = new Timer();
            TimerTask timerTask= new TimerTask() {
                @Override
                public void run() {
                    if ((customerQueue.GetFreeCustomers() != null) && (!agentQueue.isQueueEmpty())) {
                        customerQueue.GetFreeCustomers().setAgent(agentQueue.PollAgent());
                        System.out.println("Pair Created");
                    }

                }
            };

            timer.schedule(timerTask,0,1000);
        }
    }
    public Server(){
        agentQueue = new AgentQueue();
        customerQueue = new CustomerQueue();

        ServerSocket serverS= null;
        try {
            serverS = new ServerSocket(1488);

        System.out.println("Server not Started");
        try { new ServerHandler();
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket socket = serverS.accept();
                try {
                    serverList.add(new ServerSomthing(socket, this));
                    for (ServerSomthing sv : Server.serverList){
                       // System.out.print(sv.getName());
                    }
                    // добавить новое соединенние в список
                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его:
                    socket.close();
                }
            }
        } finally {
            serverS.close();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
    new Server();
    }
}