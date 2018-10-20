package men.brakh;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;
@Path("/agents")
public class  AgentQueue {
    private ConcurrentLinkedDeque<SocketUser> agentq = new ConcurrentLinkedDeque<SocketUser>();

    public void AddAgent(SocketUser user) {
        agentq.add(user);
    }

    public SocketUser PollAgent() {
        return agentq.pollFirst();
    }

    public boolean isQueueEmpty() {
        if (agentq.peekFirst() == null) {
            return true;
        } else return false;
    }
  //  @GET
 //   @Produces("application/json")
 //   public String getAllAgents() throws IOException {
//


   //     return "lol";

   // }

    @GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on "+new Date().toString();
    }

}

