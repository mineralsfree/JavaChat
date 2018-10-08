package men.brakh;

import men.brakh.Sender.Sender;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
    private SocketUser agent;
    private SocketUser customer;
    private ArrayList<Message> messageArrayList = new ArrayList<Message>();
    public Chat(User user, Sender ss){
        this.customer = new SocketUser(user,ss);
        this.agent = null;

    }
    public Type getUserType(String name){
        if (getAgent().getUser().getName().equals(name)){
            return Type.AGENT;
        }
        if (getCustomer().getUser().getName().equals(name)){
            return Type.CUSTOMER;
        }
        return null;
    }
    public void setAgent(Agent agent, Sender ss){
        this.agent = new SocketUser(agent,ss);
    }
    public SocketUser getAgent() {
        return agent;
    }
    public boolean isAgentHere(){
        if (agent==null) return false; else return true;
    }
    public SocketUser getCustomer() {
        return customer;
    }
    public Sender getCustomerSS(){
        return customer.GetSender();
    }
    public Sender getAgentSS(){
        return agent.GetSender();
    }

    public void setAgent(SocketUser agent) {
        this.agent = agent;
        for(Message mg : messageArrayList){
            this.agent.GetSender().send(mg.getString(this.agent.GetSender().toString()));
        }



    }

    public void setCustomer(SocketUser customer) {
        this.customer = customer;
    }
    public void addMessage(Message msg){
        messageArrayList.add(msg);
    }

}
