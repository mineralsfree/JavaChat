package men.brakh;


public   class User {
    private String name;
    private Type type;

   public  User(){

}
    public User(String name, Type type) {
        this.name = name;
        this.type = type;
    }
   public User(String name) {
        this.name = name;

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

