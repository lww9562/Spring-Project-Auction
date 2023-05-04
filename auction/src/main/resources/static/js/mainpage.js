//슬라이드 루프 시작하기
let loopInterval = setInterval(() =>{
    nextmove();

}, 3000);


//마우스 올라간 경우 슬라이드 루프 멈추기
slide.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
});

//마우스 벗어낫을 시 슬라이드 재시작
slide.addEventListener("mouseout", () => {
    loopInterval = setInterval(() =>  {
        nextmove();
    }, 3000);

});