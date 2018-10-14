package men.brakh;
import com.google.gson.Gson;
import men.brakh.User;
public class Message {
    private User user;
    private String message;
    private MessageType mt;

    public Message(User user, String txt,MessageType mt){
        this.user = user;
        this.message = txt;
        this.mt = mt;
    }
    public Message(){

    }
    public Message(User user, String txt,String mt){
        this.user = user;
        this.message = txt;
        this.mt = MessageType.valueOf(mt);
    }

    public MessageType getMt(){
        return mt;
    }

    public User getUser(){
        return this.user;
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static Message getMessage(String json){
        Gson gson = new Gson();
        Message msg = gson.fromJson(json, Message.class);
       return msg;

    }
    public String getString(String senderStr) {
            return (this.getJson());
    }

    public String getString(){

        if (user.getType() == Type.AGENT)
            return "[Agent] "+ user.getName()  +" '" + message + "'.";
         else if (user.getType() == Type.CUSTOMER)
            return "[Customer]" +user.getName()+" '"+message+"'.";
    else return  "[server]"+ "'"+message+"'.";
    }

    public String getMessage() {
        return message;
    }


}
