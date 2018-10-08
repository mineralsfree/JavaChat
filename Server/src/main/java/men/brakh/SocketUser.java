package men.brakh;

import men.brakh.Sender.Sender;

public class SocketUser {
    private User user;
    private Sender sender;
    public SocketUser(User user, Sender sender) {
        this.user = user;
        this.sender = sender;
    }
    public  Sender  GetSender(){
        return this.sender;
    }
    public User GetUser(){
        return this.user;
    }



    public User getUser() {
        return user;
    }
}

