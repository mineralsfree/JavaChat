
var socket = new WebSocket("ws://localhost:8081/chat");
var id = 0;
var currentid =1;
function autoris() {
    document.getElementById('chatBox').removeAttribute("style");
    let userName = document.getElementById('login').value;
    if(userName.length < 3) {
        return false;
    }
     currentUser = new User(userName, TYPE, -1);
    // showMessage(new Message(new User("Server", "NONE"), "Hello, " + currentUser.name));
    document.getElementById('subscribe').style.display = 'block';
    document.getElementById('messageHandler').style.display = '-webkit-box';
    document.getElementById('autorisation').style.display = 'none';



}
// отправить сообщение из формы publish
function sendMessage(msg) {
    socket.send(JSON.stringify(msg));
    showMessage(msg);
}

// обработчик входящих сообщений
socket.onmessage = function(event) {
    let msg = JSON.parse(event.data);
    if (msg.mt==="REG") {
        currentUser.setID(msg.message);
        return;
    }
    if (msg.mt==="ID"){
        currentid = msg.chatID;
        let chatarr= document.getElementsByClassName("chat")
        for(let i=0;i<chatarr.length;i++){
        if (chatarr[i].getAttribute("chat") ==="-1"){
            chatarr[i].setAttribute("chat",currentid)
        }
        }
    }
    if (msg.mt==="OK") {
        showMessage(msg);
    }
};
function addtab(){

    var msg = new Message(currentUser,"","REG");
    var btn = document.createElement("BUTTON");

    btn.appendChild(document.createTextNode('Chat' + ++id));

    btn.id = ("chat"+ id);
    let Messageblock = document.createElement("div");
    Messageblock.style.display = 'block';
    Messageblock.setAttribute("chat","-1");
    Messageblock.className =("chat");
    Messageblock.id = (id);
    currentid = id;
    Messageblock.appendChild(document.createTextNode("Chat" + id ))
    document.getElementById("subscribe").appendChild(Messageblock);
    btn.onclick = function() {
        for (let i = 1;i<=id;i++){
            document.getElementById(""+i).style.display = "none";
        }
        currentid = btn.id.charAt(4);
        document.getElementById(currentid).style.display = "block";
    };


    document.getElementById('messageHandler').appendChild(btn);
    sendMessage(msg);
}
function show(index){

}
// показать сообщение в div#subscribe
function showMessage(message) {
    let prefix;
    let color = "#6A6C6E";
    prefix = message.user.type;
    if (message.user.type != TYPE){
        color = "#1034A6"
    }
    if (prefix == TYPE){
        prefix = "YOU";
    }

    let messageElem = document.createElement('div');
    messageElem.style.color = color;
    let chats = document.getElementsByClassName("chat");
    if (message.chatID!=="-1"){
        for (let i=0;i<chats.length;i++){
            if (parseInt(chats[i].getAttribute("chat"))===message.chatID){
                let msgdiv = document.createElement("div");
                msgdiv.appendChild(document.createTextNode("["+prefix+"] " + message.message));
                chats[i].appendChild(msgdiv);
            }
        }
    }
}
function messageHandle(message){

    msg = new Message(currentUser, message,"OK",parseInt(document.getElementById(currentid).getAttribute("chat")));
    console.log(msg);
    sendMessage(msg)
}
socket.onclose = function(event) {
    if (event.wasClean) {
        alert('Соединение закрыто чисто');
    } else {
        alert('Обрыв соединения'); // например, "убит" процесс сервера
    }
    alert('Код: ' + event.code);
};
window.onbeforeunload = function(e) {
    if (currentUser!=null){
        let msg  = new Message(currentUser,"The User has left the chat ","EXIT");
        sendMessage(msg);
    }
    var dialogText = 'Dialog text here';
    e.returnValue = dialogText;
    return dialogText;
};

