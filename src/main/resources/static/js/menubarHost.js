// 페이지 이동 처리
$(function(){

    document.querySelector(".menubar-logo").addEventListener("click", function() {
        location.href = "/";
    });

    document.querySelector(".side-tohost").addEventListener("click", function() {
        location.href = "/";
    });

    document.querySelector(".spaceHome").addEventListener("click", function() {
        location.href = "/host/main";
    });

    document.querySelector(".notice").addEventListener("click", function() {
        location.href = "/notice";
    });

    document.querySelector(".general").addEventListener("click", function() {
        location.href = "/board";
    });

    document.querySelector(".myspaces").addEventListener("click", function() {
        location.href = "/host/spacelist";
    });

})

// 사이드 바 열고 닫기
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0px";
}
