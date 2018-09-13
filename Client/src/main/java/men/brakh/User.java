package men.brakh;


public abstract class User {
    private String name;
    private Type type;

    User(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    User(String name) {
        this.name = name;
    }    User(){

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(Type type){
        this.type = type;
    }
    public Type getType() {
        return this.type;

    }
}

