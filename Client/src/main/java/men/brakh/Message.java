package men.brakh;
import com.google.gson.Gson;
public class Message {
    private User user;
    private String message;
    private MessageType mt;
    public Message(){

    }
    public Message(User user, String txt,MessageType mt){
        this.user = user;
        this.message = txt;
    }
    public User getUser(){
        return this.user;
    }
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public Message getMessage(String json){
        Gson gson = new Gson();
       return gson.fromJson(json, Message.class);

    }

}
