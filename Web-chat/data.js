class Message {
    constructor(user, message, status = "ok") {
        this.user = user;
        this.message = message
        this.status = status;
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


}