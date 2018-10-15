package men.brakh;

import men.brakh.Sender.Sender;

public class SocketUser {
    private User user;
    private Sender sender;
    private int Chatid;
    public SocketUser(User user, Sender sender  ) {
        this.user = user;
        this.sender = sender;

    }
    public  Sender  GetSender(){
        return this.sender;
    }
    public User GetUser(){
        return this.user;
    }

    public int getChatid() {
        return Chatid;
    }

    public void setChatid(int chatid) {
        Chatid = chatid;
    }

    public User getUser() {
        return user;
    }
}

