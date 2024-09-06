let userId = document.getElementById("userId");
let phone = document.getElementById("phone");
let email = document.getElementById("email");

function finduser() { //빈칸 여부 체크 함수
    if (userId.value === "") {
        alert("아이디를 입력해주세요.");
        userId.focus();
        return false;
    }
    else if (phone.value === "") {
        alert("전화번호를 입력해주세요.");
        phone.focus();
        return false;
    }
    else if (email.value === "") {
        alert("이메일을 입력해주세요.");
        email.focus();
        return false;
    }
}