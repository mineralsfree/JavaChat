package men.brakh;

import java.util.ArrayDeque;

public class CustomerQueue {
    private ArrayDeque<Chat> customerQ = new ArrayDeque<Chat>();


    public void AddUser(User user, ServerSomthing sv){
        customerQ.add(new Chat(user,sv));

    }
    public Chat PollCustomerChat(){
        return customerQ.pollFirst();
    }
    public boolean isQueueEmpty(){
        if (customerQ.peekFirst() == null) {return true;}
        else return false;
    }
    public void addAgent(SocketUser socketUser, Chat chat){
        chat.setAgent(socketUser);
    }
    public Chat getByAgentName(String name){
        for (Chat chat : this.customerQ){
            if (chat.getAgent().GetUser().getName() == name){
                return chat;
            }
        }
        return null;
    }
}