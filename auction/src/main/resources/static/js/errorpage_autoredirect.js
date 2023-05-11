document.addEventListener('DOMContentLoaded', function() {
    setInterval(() => {
        //5초 후, 특정 페이지로 이동시키는 함수입니다.
        window.location.href = 'http://localhost:3000/main';
    }, 5000);
});