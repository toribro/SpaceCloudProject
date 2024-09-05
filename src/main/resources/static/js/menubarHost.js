// 페이지 이동 처리
document.querySelector(".menubar-logo").addEventListener("click", function() {
    location.href = "/";
});

document.querySelector(".side-tohost").addEventListener("click", function() {
    location.href = "/";
});

document.querySelector(".spaceHome").addEventListener("click", function() {
    location.href = "/main.ho";
});

document.querySelector(".notice").addEventListener("click", function() {
    location.href = "/list.no?cpage=1";
});

document.querySelector(".general").addEventListener("click", function() {
    location.href = "/list.bo?cpage=1";
});

document.querySelector(".myspaces").addEventListener("click", function() {
    location.href = "/myspace.sp";
});

// 사이드 바 열고 닫기
function openNav() {
    document.getElementById("mySidenav").style.width = "320px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0px";
}
