package men.brakh;

import men.brakh.Sender.Sender;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Chat {
    private SocketUser agent;
    private SocketUser customer;
    private int id;
    private ArrayList<Message> messageArrayList = new ArrayList<Message>();
    public Chat(User user, Sender ss,int id){
        this.customer = new SocketUser(user,ss);
        this.agent = null;
        this.id = id;
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
    public Type getUserType(int id){
        if (getAgent().getUser().getId()==id){
            return Type.AGENT;
        }
        if (getCustomer().getUser().getId()==id){
            return Type.CUSTOMER;
        }
        return null;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
