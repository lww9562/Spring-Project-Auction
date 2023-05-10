document.addEventListener('DOMContentLoaded', function() {

//swiper(카테고리) start

/**
    필요한 클래스 : 전체를 감싸는 클래스 1개
                    버튼 왼쪽, 오른쪽 클래스 각각 1개

*/

//.cate라는 클래스를 찾아서 변수로 대입
const cateScroll = document.querySelector(".cate");
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
});