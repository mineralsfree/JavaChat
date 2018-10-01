
var MsgType = Object.freeze({REG:1,EXIT:2,OK:3,LEAVE:4})
var socket = new WebSocket("ws://localhost:8081/chat");
function autoris() {
    let userName = document.getElementById('login').value;
    if(userName.length < 3) {
        return false;
    }

     currentUser = new User(userName, TYPE);
  	 var  msg = new Message(currentUser, "", MsgType.REG);
    showMessage(new Message(new User("Server", "NONE"), "Hello, " + currentUser.name));
    sendMessage(msg);
    document.getElementById('subscribe').style.display = 'block';
    document.getElementById('autorisation').style.display = 'none';

}
// отправить сообщение из формы publish
function sendMessage(msg) {
    let message = new Message(currentUser, "msg",MsgType.OK);
    document.getElementById('messageField').value ="";
   socket.send(JSON.stringify(message));
    showMessage(msg);
}

// обработчик входящих сообщений
socket.onmessage = function(event) {
    let msg = JSON.parse(event.data);
  showMessage(msg.message);
};

// показать сообщение в div#subscribe
function showMessage(message) {
  let messageElem = document.createElement('div');
  messageElem.appendChild(document.createTextNode(message));
  document.body.appendChild(messageElem);
}