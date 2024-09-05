function movePage(loginUser) {
    if (loginUser!=null) {
        location.href = "host/pre";
    } else {
        alert("로그인 이후에 이용가능합니다!!");
    }
}
