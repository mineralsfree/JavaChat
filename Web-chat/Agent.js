
var socket = new WebSocket("ws://localhost:8081/chat");
var id = 0;
var currentid =id;
function autoris() {
    document.getElementById('chatBox').removeAttribute("style");
    let userName = document.getElementById('login').value;
    if(userName.length < 3) {
        return false;
    }
    let messageElem = document.createElement('div');
    document.getElementById('subscribe').appendChild(messageElem);
    currentUser = new User(userName, TYPE,-1);
    var  msg = new Message(currentUser, "", "REG");
    // showMessage(new Message(new User("Server", "NONE"), "Hello, " + currentUser.name));
    sendMessage(msg);
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

    showMessage(msg);
};
function addtab(){
    var msg = new Message(currentUser,"","REG");
    var btn = document.createElement("BUTTON");

    btn.appendChild(document.createTextNode('Chat' + ++id));
    btn.id = ("chat"+ id);

    btn.onclick = function() {
        for (let i = 1;i<=id;i++){
            document.getElementById('messages' +i).style.display = "none";
            document.getElementById((btn.childNodes[0].textContent)).style.display = "block";

        } };

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
    messageElem.appendChild(document.createTextNode("["+prefix+"] " + message.message));
    let node = document.getElementById('subscribe').childNodes[currentid]
    node.appendChild(document.createTextNode("["+prefix+"] " + message.message));
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

