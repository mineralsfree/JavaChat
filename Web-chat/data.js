class Message {
    constructor(user, message, mt = "OK",chatid = -1) {
        this.user = user;
        this.message = message
        this.mt = mt;
        this.chatid = chatid;

    }
    getUserType(){
        return this.user.getType();
    }
}
class User {
    constructor (name,type,id){
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


}