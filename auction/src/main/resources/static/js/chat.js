const chatWs = new WebSocket("ws://localhost:3000/chat");

chatWs.addEventListener("open", function(e) {
    //
});

chatWs.addEventListener("close", function(e) {
    // 종료
});

chatWs.addEventListener("message", function(e) {

    const params = new URLSearchParams(location.search);
    const sender = params.get("sender");
    const data = JSON.parse(e.data);

    if (sender == data.sender) {
        return;
    }

    alert(`${data.receiver}님, ${data.message} FROM ${data.sender}`);

    const message = prompt("메세지 입력");

    const sendData = {
        sender,
        receiver : "시스템",
        message
    };

    chatWs.send(JSON.stringify(sendData));
});