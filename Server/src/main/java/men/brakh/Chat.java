package men.brakh;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
    private SocketUser agent;
    private SocketUser customer;
    private ArrayList<Message> messageArrayList = new ArrayList<Message>();
    public Chat(User user, ServerSomthing ss){
        this.customer = new SocketUser(user,ss);
        this.agent = null;

    }
    public void setAgent(Agent agent, ServerSomthing ss){
        this.agent = new SocketUser(agent,ss);
    }
    public SocketUser getAgent() {
        return agent;
    }

    public SocketUser getCustomer() {
        return customer;
    }
    public ServerSomthing getCustomerSS(){
        return customer.GetServerSomthing();
    }
    public ServerSomthing getAgentSS(){
        return agent.GetServerSomthing();
    }

    public void setAgent(SocketUser agent) {
        this.agent = agent;
    }

    public void setCustomer(SocketUser customer) {
        this.customer = customer;
    }
    public void addMessage(Message msg){
        messageArrayList.add(msg);
    }

}
