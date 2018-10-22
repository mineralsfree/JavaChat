class User {
    constructor (name,type,chatID){
        this.name = name;
        this.type = type;
        this.id =id;
    }
    getName(){
        return this.name;
    }
    getType(){
        return this.type;
    }
    setID(id){
        this.id = id;
    }

    getUserType(){
        return this.user.getType();
    }
}