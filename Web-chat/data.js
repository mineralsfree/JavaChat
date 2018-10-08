class Message {
    constructor(user, message, mt = "OK") {
        this.user = user;
        this.message = message
        this.mt = mt;
    }
    getUserType(){
        return this.user.getType();
    }
}
class User {
    constructor (name,type){
        this.name = name;
        this.type = type;
    }
    getName(){
        return this.name;
    }
    getType(){
        return this.type;
    }


}