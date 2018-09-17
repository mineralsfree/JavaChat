package men.brakh;

public class SocketUser {
    private User user;
    private ServerSomthing sv;
    SocketUser(User user, ServerSomthing sv) {
        this.user = user;
        this.sv = sv;
    }
    public  ServerSomthing  GetServerSomthing(){
        return this.sv;
    }
    public User GetUser(){
        return this.user;
    }



    public User getUser() {
        return user;
    }
}

