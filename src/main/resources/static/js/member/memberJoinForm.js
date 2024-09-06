function idCheck() {
    let userId = document.getElementById("userId");
    let cantid = document.querySelector(".cantid");
    let useableid = document.querySelector(".useableid");

    $.ajax({
        type: "POST",
        url: "idCheck.me",
        data: {
            checkId: userId.value
        },
        success: function (res) {
            if (!isVailId(userId.value)) {
                cantid.style.display = "block";
                useableid.style.display = "none";
                return;
            }

            if (res === "NNNNY") {
                cantid.style.display = "none";
                if (confirm("이 아이디를 사용하시겠습니까?")) {
                    useableid.style.display = "block";
                    userId.style.width = "127%";
                } else {
                    useableid.style.display = "none";
                    userId.focus();
                }
            } else {
                cantid.style.display = "block";
                useableid.style.display = "none";
                userId.style.width = "120%";
                userId.focus();
            }
        },
        error: function (err) {
            alert("실패 에러 :" + err);
        }
    });
}

function joinUser() {
    let cantid = document.querySelector(".cantid");
    let userId = document.getElementById("userId");

    if (userId.value === "") {
        alert("아이디를 입력해주세요.");
        userId.focus();
        return false;
    } else if (cantid.style.display !== "none") {
        alert("사용할 수 없는 아이디입니다. 다시 입력해주세요.");
        userId.focus();
        return false;
    }

    // 생략된 다른 조건들...

    return true;
}

function onb() {
    let userPwd = document.getElementById("userPwd");
    let userPwdCheck = document.getElementById("userPwdCheck");
    let usealbePwd = document.querySelector(".usealbePwd");
    let cantPwdCheck = document.querySelector(".cantPwdCheck");
    let cantPwd = document.querySelector(".cantPwd");

    if (!isValidPassword(userPwd.value)) {
        cantPwd.style.display = "block";
        cantPwdCheck.style.display = "none";
        usealbePwd.style.display = "none";
    } else {
        cantPwd.style.display = "none";
    }

    if (userPwd.value !== userPwdCheck.value && cantPwd.style.display === "none") {
        cantPwdCheck.style.display = "block";
        usealbePwd.style.display = "none";
    } else if (userPwd.value === userPwdCheck.value && userPwd.value !== "" && cantPwd.style.display === "none") {
        usealbePwd.style.display = "block";
        cantPwdCheck.style.display = "none";
    }
}

function isVailId(userId) {
    return /^(?=.{6,20}$)[a-zA-Z0-9]+$/.test(userId);
}

function isValidPassword(password) {
    return password.length >= 8 && password.length <= 20 && /[a-zA-Z]/.test(password) && /\d/.test(password) && /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(password);
}
