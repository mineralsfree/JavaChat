package men.brakh;


public   class User {
    private String name;
    private Type type;
    private int id;

   public  User() {
   }
    public User(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    public User(String name, Type type, int id) {
        this.name = name;
        this.type = type;
    }
    public void setId(int id){
        this.id = id;
    }
    public User(String name) {
        this.name = name;
        this.id = -1;

    }

    public User(String name, int id) {
        this(name);
        this.id = id;
    }

    public int getId() {
        return id;
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

