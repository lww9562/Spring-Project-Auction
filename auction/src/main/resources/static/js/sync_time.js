const ws = new WebSocket("ws://localhost:3000/data");

//연결이 열릴 때
ws.addEventListener("open", function(e){
    //console.log("연결 성립", e);
    setInterval(function() {
        ws.send("now");
    }, 1000);

});
//연결이 닫힐 때
ws.addEventListener("close", function(e){
    //console.log("연결 종료", e);
    const data = e.data;
    const data = new Date(data);
    console.log(data);
});
//메세지 수신 시
ws.addEventListener("message", function(data){
    console.log("연결", data);
});

