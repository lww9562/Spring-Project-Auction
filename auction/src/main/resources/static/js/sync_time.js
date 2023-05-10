const ws = new WebSocket("ws://localhost:3000/data");

// 연결 성립시
ws.addEventListener("open",function(e){
  //console.log("연결 성립", e)

  setInterval(function(){
      ws.send("now");
  }, 3000)

});

// 연결 닫힐때
ws.addEventListener("close",function(e){
    //console.log("연결종료",e)
});


// 메세지 수신 시
ws.addEventListener("message",function(e){

    const data = e.data;
    const date = new Date(data);

    console.log(data);
    console.log(date);
});



/**



*/