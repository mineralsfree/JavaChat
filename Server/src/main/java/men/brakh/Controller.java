package men.brakh;

import men.brakh.Sender.APISender;
import men.brakh.Sender.Sender;
import men.brakh.handler.Handler;
import men.brakh.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("API")

public class Controller {
    public static Server server;
    @Autowired
    private void setServer(Server server){
        Controller.server = server;
    }


    @GetMapping("/agents")
        public List<SocketUser> Agents(@RequestParam Map<String,String> allRequestParams){
        LinkedList<SocketUser> users =  new LinkedList<>();
        if (allRequestParams == null){
            allRequestParams = new HashMap<>();
            allRequestParams.put("free","false");
        }
        synchronized (server.agentQueue){
        if(server.agentQueue!=null) {
            for (SocketUser su : server.agentQueue.getAgents()) {
                users.addFirst(su);
            }
        }
        }
        synchronized (server.customerQueue){
        if(server.customerQueue!=null && !allRequestParams.get("free").equals("true")) {
            for (SocketUser su : server.customerQueue.getAllAgents()) {
                users.addFirst(su);
                }
            }
        }
        return users;
    }
    @GetMapping("/agents/{id}")
    public SocketUser Agent(@PathVariable int id){
        SocketUser result = server.agentQueue.getAgentByID(id);
        if (result == null)
        result = server.customerQueue.getbyAgentID(id).getAgent();
        return result;
    }
    @GetMapping("/agents/amount")
    public int Amount(){
       return server.agentQueue.getLength();
    }
    @GetMapping("/customers")
    public List<SocketUser> Customer(){
        LinkedList<SocketUser> users =  new LinkedList<>();
        if(server.customerQueue!=null){
            for (SocketUser su: server.customerQueue.getWaitingCustomers()){
                users.addFirst(su);
            }
        }
        return users;
    }
    @GetMapping("/customers{id}")
    public SocketUser CustomerbyID(@PathVariable int id){
       return server.customerQueue.getByID(id).getCustomer();

    }
    @PostMapping("/agent")
        public Message regAgent(@RequestBody String name){
        User user = new User(name,Type.AGENT,-1);
        APISender sender = new APISender();
        new MessageHandler(new Message(user,"",MessageType.REG),server,sender);
        return sender.returnMessage();
        }
    @PostMapping("/client")
    public  Message regClient(@RequestBody  String name){
        User user = new User(name,Type.CUSTOMER,-1);
        APISender sender = new APISender();
        new MessageHandler(new Message(user,"",MessageType.REG),server,sender);
        return sender.returnMessage();
    }
    @PutMapping("/agent/send")
    public void AgentSend(@RequestParam(value = "msg") String str,@RequestParam(value = "id") int id) {

        if (server.agentQueue.getAgentByID(id) == null) {
            if (server.customerQueue.getbyAgentID(id) != null) {
                User user = server.customerQueue.getbyAgentID(id).getAgent().GetUser();
                Message msg = new Message(user, str, MessageType.OK);
                APISender apiSender = new APISender();
                new MessageHandler(msg, server, apiSender);
                apiSender.send(msg);
            }
        }
    }
        @PutMapping("/customer/send")
        public void CustomerSend(@RequestParam(value = "msg") String str,@RequestParam(value = "id") int id){


                if (server.customerQueue.getbyCustomerID(id)!=null){
                    User user = server.customerQueue.getbyCustomerID(id).getCustomer().GetUser();
                    Message msg = new Message(user,str,MessageType.OK);
                    APISender apiSender = new APISender();
                    new MessageHandler(msg,server,apiSender);
                    apiSender.send(msg);
                }



    }

}