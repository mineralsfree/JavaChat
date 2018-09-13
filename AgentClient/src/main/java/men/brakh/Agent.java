package men.brakh;
import men.brakh.Type;

public class Agent extends User {
    Agent(String name){
       super(name);
        setType(Type.AGENT);
    }
}
