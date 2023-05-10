
window.addEventListener("DOMContentLoaded", function() {
	const regData = document.getElementById("timer");
	const dataset = regData.dataset;
    const regdt = dataset.regdt;
    console.log("regdt : " + regdt);
    var regDt = new Date(regdt);
    console.log("regDt(changeDateType) : " + regDt)

    const utc = regDt.getTime() + (regDt.getTimezoneOffset() * 60 *1000);
    const KR_TIME_DIFF = 9*60*60*1000;
    const kr_curr_regDt = new Date(utc + (KR_TIME_DIFF));
    var open = kr_curr_regDt.setDate(kr_curr_regDt.getDate()+3);

	const nws = new WebSocket("ws://localhost:3000/data");

	// 연결 성립시
	nws.addEventListener("open", function(e) {
		setInterval(function() {
			nws.send("now");
		}, 1000);
	});

    nws.addEventListener("message", function(e) {
        const data = e.data;
        const date = new Date(data);

        var now = date;
        var nt = now.getTime();
        var ot = open; // 오픈시간만 가져온다

		console.log(nt);
        console.log(ot);

    	if(nt<ot){
    		sec = parseInt(ot - nt) / 1000;
    		hour = parseInt(sec/60/60);
    		sec = (sec - (hour*60*60));
    		min = parseInt(sec/60);
    		sec = parseInt(sec-(min*60));

    		if(hour<10){
    			hour="0"+hour;
    		}
    		if(min<10){
    			min="0"+min;
    		}
    		if(sec<10){
    			sec="0"+sec;
    		}
    		$("#d-day-hour").html(hour);
    		$("#d-day-min").html(min);
    		$("#d-day-sec").html(sec);
    		console.log(hour);
    	}
    });

});