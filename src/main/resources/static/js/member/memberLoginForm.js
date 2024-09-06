function loginuser() {
    let userId = document.getElementById("userId");
    let userPwd = document.getElementById("userPwd");

    if (userId.value === "") {
        alert("아이디를 입력해주세요.");
        userId.focus();
        return false;
    } else if (userPwd.value === "") {
        alert("비밀번호를 입력해주세요.");
        userPwd.focus();
        return false;
    }
}