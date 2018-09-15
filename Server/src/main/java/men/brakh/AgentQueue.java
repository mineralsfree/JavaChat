package men.brakh;

import java.util.ArrayDeque;

public class AgentQueue {
    private ArrayDeque<SocketUser> agentq = new ArrayDeque<SocketUser>();
    public void AddAgent(SocketUser user){
        agentq.add(user);
    }
    public SocketUser PollAgent(){
        return agentq.pollFirst();
    }
    public boolean isQueueEmpty(){
        if (agentq.peekFirst() == null) {return true;}
        else return false;
    }
}
