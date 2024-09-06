$(function(){
    // 메인페이지로 이동
    $(".menubar-logo").click(function(){
        location.href = "/";
    });

// 홈으로 이동
    $(".home").click(function(){
        location.href = "/";
    });

// 호스트 페이지로 이동
    $(".side-tohost").click(function(){
        location.href = "/host";
    });

// 공지사항 페이지로 이동
    $(".notice").click(function(){
        location.href = "/notice";
    });

// 일반 게시판 페이지로 이동
    $(".general").click(function(){
        location.href = "/board";
    });

// 찜한 공간으로 이동
    $(".picked-list").click(function(){
        location.href = "/guest/pickedList";
    });

// 예약 페이지로 이동
    $(".book-list").click(function(){
        location.href = "/guest/reservationList";
    });



})


// 사이드 바 열기
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

// 사이드 바 닫기
function closeNav() {
    document.getElementById("mySidenav").style.width = "0px";
}
