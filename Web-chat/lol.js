

var socket = new WebSocket("ws://localhost:8081/chat/chat");
function autoris() {
    let userName = document.getElementById('login').value;
    if(userName.length < 3) {
        return false;
    }

    var currentUser = new User(userName, TYPE);
  	 var  msg = new Message(currentUser, "", "reg");
    showMessage(new Message(new User("Server", "NONE"), "Hello, " + currentUser.name));
    sendMessage(msg);
    document.getElementById('subscribe').style.display = 'block';
    document.getElementById('autorisation').style.display = 'none';

}
// отправить сообщение из формы publish
function sendMessage(msg) {
   socket.send(JSON.stringify(msg));
}

// обработчик входящих сообщений
socket.onmessage = function(event) {
    let msg = JSON.parse(event.data);
  showMessage(msg);
};

// показать сообщение в div#subscribe
function showMessage(message) {
  let messageElem = document.createElement('div');
  messageElem.appendChild(document.createTextNode(message));
  document.body.appendChild(messageElem);
}