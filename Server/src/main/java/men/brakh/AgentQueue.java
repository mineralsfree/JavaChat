package men.brakh;


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




}

