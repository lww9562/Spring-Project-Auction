document.addEventListener('DOMContentLoaded', function() {


//progress bar + slide img start
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
//progress bar + slide img start end

//carousel

//swiper(카테고리) start

const cateScroll = document.querySelectorAll(".catewrapper");
const currentCateScroll = element.scrollLeft;

console.log(currentCateScroll);

var Scroll_btn_left = document.querySelector(".car-prev");
var Scroll_btn_right = document.querySelector(".car-next");

Scroll_btn_left.addEventListener("click", () => {
    cateScroll.scrollTo()
    console.log("왼쪽!");

});


Scroll_btn_right.addEventListener("click", () => {
    console.log("오른쪽!");

});


let swipeItems = document.querySelectorAll(".swiper-slide");



// var swiper = new Swiper(".mySwiper", {
//      slidesPerView: swipeItems.length/2,
//      spaceBetween: 30,
//    initialSlide: 0,
//      centeredSlides: true,
//      /**
//      //centerInsufficientSlides: true,
//
////      centeredSlidesBounds : true,
////       hashNavigation: {
////            watchState: true,
////        },
//    */
//      pagination: {
//        el: ".swiper-pagination",
//        type:"progressbar",
//        clickable: true,
//      },
//      navigation: {
//              nextEl: ".swiper-button-next",
//              prevEl: ".swiper-button-prev",
//         },
//    });
//     console.log(swipeItems);

    //swiper end


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


// carousel 외부 라이브러리 사용 실패시 고쳐서 사용해야 할 코드 S
//  const carouseInner = document.querySelector('.cate');
//  const carouseItems = carouseInner.querySelectorAll('.catelist');
//  const carPrev = document.querySelector('.car-prev');
//  const carNext = document.querySelector('.car-next');
//
//  console.log(carouseItems);
//
//  let cateIndex = 0;
//  const maxOffset = carouseItems[0].scrollWidth - carouseInner.offsetWidth;
//
//  function moveCarousel(cateoffset) {
//    cateIndex += cateoffset;
//    cateIndex = Math.max(cateIndex, 0);
//    cateIndex = Math.min(cateIndex, maxOffset);
//    carouseInner.style.transform = `translateX(-${cateIndex}px)`;
//
//    console.log(cateIndex);
//    console.log(carouseInner.offsetWidth);
//    console.log(carouseInner.scrollWidth);
//  }
//
//  carPrev.addEventListener('click', () => moveCarousel(-200));
//  carNext.addEventListener('click', () => moveCarousel(200));
// carousel 외부 라이브러리 사용 실패시 고쳐서 사용해야 할 코드 E



});