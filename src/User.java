import java.net.Socket;
import java.time.Clock;
import java.time.LocalDateTime;

public abstract class User {
	private String name;
	private int port;
	private String adress;
	private Socket socket;
	private LocalDateTime registrationTime;
public enum Type {client, agent
		
	}
	private Type type;
	public String getAdress() {
		return adress;
	}
	public int getPort() {
		return port;
	}
	public Type getType() {
		return type;
	}
	public User(String name,Type type) {
		this.setName(name);
		this.type = type;
	}
	public User(String name,Type type, LocalDateTime registrationTime) {
		this.setName(name);
		this.type = type;
		this.registrationTime = registrationTime;
	}
	public String getName() {
		return name;
	}
	public LocalDateTime getRegTime() {
		return registrationTime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}
	

}
