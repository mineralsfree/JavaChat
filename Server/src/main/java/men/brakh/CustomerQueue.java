package men.brakh;

import men.brakh.Sender.Sender;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CustomerQueue {
    private ConcurrentLinkedDeque<Chat> customerQ = new ConcurrentLinkedDeque<Chat>();
    private int chatid = 0;
    public void AddUser(User user, Sender sender){
        customerQ.add(new Chat(user,sender, getNextid()));
    }

synchronized private int getNextid(){
        return chatid++;
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
                try {
                    if (chat.getAgent().GetUser().getName().equals(name)){
                        return chat;
                    }
                } catch (NullPointerException e ){

                }

        }
        return null;
    }

    public Chat getdAgentID(int id){
        for (Chat chat : this.customerQ){
            try {
                if (chat.getAgent().GetUser().getId() == id){
                    return chat;
                }
            } catch (NullPointerException e ){

            }

        }
        return null;
    }

    public Chat getByUserName(String name){
        for (Chat chat : this.customerQ){
            if (chat.getCustomer().GetUser().getName().equals(name)){
                return chat;
            }
        }
        return null;
    }

    public Chat getByID(int id){
        for (Chat chat : this.customerQ){
            if ((chat.getCustomer().GetUser().getId()== id) || chat.getAgent().GetUser().getId() == id){

            }
        }
        return null;
    }
    public void DeleteChat(Chat chat){
        customerQ.removeFirstOccurrence(chat);
    }
    public Chat GetFreeCustomers(){
        for (Chat chat : customerQ){
            if (chat.getAgent()==null){
                return chat;
            }
        }
        return null;
    }



}