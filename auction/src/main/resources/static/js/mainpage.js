document.addEventListener('DOMContentLoaded', function() {


//progress bar + slide img start

/**
    <div class="slide"> 안에 이미지 파일을 넣으면 자동으로
    반영되게 되어 있습니다.
    무언가 슬라이드에 대한 설정을 바꾸는게 아닌 이상
    여기를 수정하실 필요는 없을것이라 생각합니다...
*/
const slide = document.querySelector(".slide");
let slideWidth = slide.clientWidth;

const prevBtn = document.querySelector(".backbutton");
const nextBtn = document.querySelector(".forwardbutton");

let slideItems = document.querySelectorAll(".slideimg");
const maxSlide = slideItems.length;

let currSlide = 1;
//프로그레스 바를 넣을 예정
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
//progress bar + slide img start end




//swiper(카테고리) start

/**
    필요한 클래스 : 전체를 감싸는 클래스 1개
                    버튼 왼쪽, 오른쪽 클래스 각각 1개

*/

//.catewrapper라는 클래스를 찾아서 변수로 대입
const cateScroll = document.querySelector(".catewrapper");

//스크롤 이벤트 값을 저장하고 사용하기 위한 변수
var nowCateScroll = 0;

//어떤 버튼이든 3번 누르면 끝쪽으로 가게 설정.
//이 값을 늘리면 버튼 한번당 이동하는 카테고리 수가 줄어들고,
//줄이면 한번에 이동하는 카테고리가 많아집니다. (3 기준 1번 누를시 3분의 1 이동)
var cate_Scrolling_value=3;

//버튼과 스크롤 연동
var Scroll_btn_left = document.querySelector(".car-prev .car-prev-svg");
var Scroll_btn_right = document.querySelector(".car-next .car-next-svg");

//이전 버튼에 대한 이벤트 리스너
Scroll_btn_left.addEventListener("click", () => {
    resetCateScroll();
     nowCateScroll -= cateScroll.offsetWidth/cate_Scrolling_value;
       cateScroll.scrollTo(nowCateScroll,0);
       resetCateScroll();
});

//다음 버튼에 대한 이벤트 리스너
Scroll_btn_right.addEventListener("click", () => {
        resetCateScroll();
       nowCateScroll+=cateScroll.offsetWidth/cate_Scrolling_value;
       cateScroll.scrollTo(nowCateScroll,0);
       resetCateScroll();
});

//버튼을 눌렀을때 스크롤 값이 최대, 최소를 넘기는 것을 방지하고,
//스크롤 값을 올바른 값으로 재배치 하기 위한 콜백함수
function resetCateScroll(){
     if(nowCateScroll < 0){
                cateScroll.scrollTo(0,0);
                nowCateScroll = 0;
            }
     if(nowCateScroll > cateScroll.offsetWidth){
                cateScroll.scrollTo(cateScroll.offsetWidth,0);
                nowCateScroll = cateScroll.offsetWidth;
     }
}

    //swiper(카테고리) end


    //top_button start
    var backToTop = () => {
      window.addEventListener('scroll', () => {
        if (document.querySelector('html').scrollTop > 100) {
          document.querySelector('.top_button').style.display = "block";
        } else {
          document.querySelector('.top_button').style.display = "none";
        }
      });

      document.querySelector('.top_button').addEventListener('click', () => {
        window.scrollTo({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
      })
    };
    backToTop();
    //top_button end


});