package men.brakh;




import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentLinkedDeque;
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
    public SocketUser[]  getAgents(){
        SocketUser arr[] = new SocketUser[agentq.size()];
                arr = agentq.toArray(arr);
        return arr;
    }
    public  SocketUser getAgentByID(int id){
        for(SocketUser su:agentq){
            if (su.getUser().getId() == id){
                return su;
            }
        }
        return null;
    }
    public int getLength(){
        return this.agentq.size();
    }



}

