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
                try {
                    if (chat.getAgent().GetUser().getName().equals(name)){
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
    public Chat GetFreeCustomers(){
        for (Chat chat : customerQ){
            if (chat.getAgent()==null){
                return chat;
            }
        }
        return null;
    }


}