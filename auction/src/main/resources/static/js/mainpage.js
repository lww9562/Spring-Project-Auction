document.addEventListener('DOMContentLoaded', function() {

const slide = document.querySelector(".slide");
let slideWidth = slide.clientWidth;

const prevBtn = document.querySelector(".backbutton");
const nextBtn = document.querySelector(".forwardbutton");

let slideItems = document.querySelectorAll(".slideimg");
const maxSlide = slideItems.length;

let currSlide = 1;
//프로그레스 바를 넣을 예정
//
const progressbar = document.querySelector(".progressbar-fill");
function updateProgress() {
  progressbar.style.width = `${(currSlide / maxSlide) * 100}%`;
}



const startSlide = slideItems[0];
const endSlide = slideItems[slideItems.length-1];
const startElem = document.createElement("div");
const endElem = document.createElement("div");

endSlide.classList.forEach((c) => endElem.classList.add(c));
endElem.innerHTML = endSlide.innerHTML;

startSlide.classList.forEach((c) => endElem.classList.add(c));
startElem.innerHTML = startSlide.innerHTML

slideItems[0].before(endElem);
slideItems[slideItems.length -1].after(startElem);

slideItems = document.querySelectorAll(".slideimg");

let offset = slideWidth + currSlide;

slideItems.forEach((i) => {
    i.setAttribute("style", `left:${-offset}px`);
});



function nextMove(){
    currSlide++;
    if(currSlide <= maxSlide){
        const offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `left : ${-offset}px`);
        });
    updateProgress();
    }else{
        currSlide = 0;
        let offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
              i.setAttribute("style", `transition: ${0}s; left: ${-offset}px`);
            });
        currSlide++;
        offset = slideWidth * currSlide;
        setTimeout(() => {
            slideItems.forEach((i) => {
                i.setAttribute("style", `transition:${0.15}s; left:${-offset}px`);
            });
        }, 0);
        updateProgress();
    }
}

function prevMove(){
    currSlide--;
    if(currSlide > 0){
        const offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `left : ${-offset}px`);
        });
       updateProgress();
    }else{
        currSlide = maxSlide + 1;
        let offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            i.setAttribute("style", `transition:${0}s; left:${-offset}px`);
        });
        currSlide--;
        offset = slideWidth * currSlide;
        setTimeout(() => {
            slideItems.forEach((i) => {
                i.setAttribute("style", `transition:${0.15}s; left:${-offset}px`);
            });
        }, 0);
        updateProgress();
    }
}

nextBtn.addEventListener("click", () => {
    nextMove();
});

prevBtn.addEventListener("click", () => {
    prevMove();
});

window.addEventListener("resize", () => {
    slideWidth = slide.clientWidth;
});

let startPoint = 0;
let endPoint = 0;

slide.addEventListener("mousedown", (e) => {
    startPoint = e.pageX;
});

slide.addEventListener("mouseup", (e) => {
    endPoint = e.pageX;
    if(startPoint < endPoint){
        prevMove();
    } else if(startPoint > endPoint){
        nextMove();
    }
});


slide.addEventListener("touchstart", (e) => {
  startPoint = e.touches[0].pageX; // 터치가 시작되는 위치 저장
});
slide.addEventListener("touchend", (e) => {
  endPoint = e.changedTouches[0].pageX; // 터치가 끝나는 위치 저장
  if (startPoint < endPoint) {
    // 오른쪽으로 스와이프 된 경우
    prevMove();
  } else if (startPoint > endPoint) {
    // 왼쪽으로 스와이프 된 경우
    nextMove();
  }
});

//슬라이드 루프 시작하기
let loopInterval = setInterval(() =>{
    nextMove();

}, 3000);


//마우스 올라간 경우 슬라이드 루프 멈추기
slide.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
});

//마우스 벗어낫을 시 슬라이드 재시작
slide.addEventListener("mouseout", () => {
    loopInterval = setInterval(() =>  {
        nextMove();
    }, 3000);

});

});