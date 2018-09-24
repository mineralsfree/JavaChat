var socket = new WebSocket("ws://localhost:8081/chat/chat");
WebSocket.onopen = function(){
  alert ("соединение установлено");

};
// отправить сообщение из формы publish
function sendMessage() {
  var outgoingMessage =  document.getElementById("messageField").value;

  socket.send(outgoingMessage);
  return false;
};

// обработчик входящих сообщений
socket.onmessage = function(event) {
  var incomingMessage = event.data;
  showMessage(incomingMessage);
};

// показать сообщение в div#subscribe
function showMessage(message) {
  var messageElem = document.createElement('div');
  messageElem.appendChild(document.createTextNode(message));
  document.getElementById('subscribe').appendChild(messageElem);
}
