package men.brakh;
        import men.brakh.Listener.WebSocketListener;
        import org.springframework.context.annotation.Configuration;


        import java.io.IOException;
        import java.util.LinkedList;

@Configuration
public class Server {
    private int id = 0;
    public static final int PORT = 1488;
    public AgentQueue agentQueue;
    public CustomerQueue customerQueue;
    public Logger logger;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<ServerSomthing>(); // список всех нитей - экземпляров
    // сервера, слушающих каждый своего клиента



    public synchronized void checkFreeAgents(){
        Chat customerChat = customerQueue.GetFreeCustomers();
        if (( customerChat!= null) && (!agentQueue.isQueueEmpty())) {
            SocketUser agent = agentQueue.PollAgent();
            Chat CustomerChat = customerQueue.GetFreeCustomers();
            CustomerChat.setAgent(agent);
            String chatid = String.valueOf(CustomerChat.getId());
            agent.GetSender().ServerRegID(chatid);
            agent.GetSender().ServerSend("U've been connected to "+CustomerChat.getCustomer().GetUser().getName()+" be polite, your chat is logged",Integer.parseInt(chatid));
            CustomerChat.getCustomer().GetSender().ServerSend("U've been connected to "+CustomerChat.getAgent().GetUser().getName()+" be polite, your chat is logged",Integer.parseInt(chatid));
            logger.log(String.format("Pair [Agent] %s and [Customer] %s", agent.GetUser().getName(), customerChat.getCustomer().GetUser().getName()," Created"));
        }
    }
    public Server() throws IOException {
        agentQueue = new AgentQueue();
        customerQueue = new CustomerQueue();
        logger = new Logger("Log.txt");
    //    SocketListener socketListener =  new SocketListener(PORT,this);
        WebSocketListener webSocketListener = new WebSocketListener(this);
   //     try {
          //  socketListener.join();
      //      webSocketListener.join();
  //      } catch (InterruptedException e) {
    //        e.printStackTrace();
   //     }

        System.out.println("Server not Started");
    }
    public synchronized int getNewId() {
        return ++id;
    }



    public static void main(String[] args) throws IOException {
    new Server();
    }
}
