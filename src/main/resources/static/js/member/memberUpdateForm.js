function pwdUpdate(){//비밀번호 변경버튼 클릭시
    let oldPwd = document.getElementById("oldPwd");
    let userPwd = document.getElementById("userPwd");
    let userPwdCheck = document.getElementById("userPwdCheck");
    document.querySelectorAll(".pwd-color").forEach(function(el){ //모든 pwd-color 폰트색 검정으로 변경
        el.style.color="black";
    })
    oldPwd.removeAttribute("readonly");
    oldPwd.placeholder=" 비밀번호 입력(문자,숫자,특수문자 포함 8~20자)";
    userPwd.removeAttribute("readonly");
    userPwd.placeholder=" 비밀번호 입력(문자,숫자,특수문자 포함 8~20자)";

    userPwdCheck.removeAttribute("readonly");
    userPwdCheck.placeholder=" 비밀번호 재입력";
}

function canclePwdUpdate(){//비밀번호 변경 취소버튼 클릭시
    let checkResult =document.getElementById("oldPwd-area");
    let usealbePwd = document.querySelector(".usealbePwd");
    let cantPwdCheck = document.querySelector(".cantPwdCheck");
    let cantPwd = document.querySelector(".cantPwd");

    let oldPwd = document.getElementById("oldPwd");
    let userPwd = document.getElementById("userPwd");
    let userPwdCheck = document.getElementById("userPwdCheck");
    document.querySelectorAll(".pwd-color").forEach(function(el){ //모든 pwd-color 폰트색 회색으로 변경
        el.style.color="#c2c0c0";

        oldPwd.setAttribute("readonly",true);        //이 아래부터는 싹다 전 모습으로 되돌리기
        oldPwd.placeholder="";
        oldPwd.value="";

        userPwd.setAttribute("readonly",true);
        userPwd.placeholder="";
        userPwd.value="";

        userPwdCheck.setAttribute("readonly",true);
        userPwdCheck.placeholder="";
        userPwdCheck.value="";

        checkResult.style.display="none";
        usealbePwd.style.display="none";
        cantPwdCheck.style.display="none";
        cantPwd.style.display="none";
    })
}


$(function(){ //현재 비밀번호 일치하는지 확인 (0.5초)
    const pwdInput = document.getElementById("oldPwd");
    let eventFlage;
    pwdInput.onkeyup = function(ev){
        clearTimeout(eventFlage);
        const str =ev.target.value;
        if(str.trim().length>=4){
            eventFlag = setTimeout(function(){
                console.log("전송");
                $.ajax({
                    url:"pwdCheck.me",
                    data:{
                        userId: document.getElementById("userId").value,
                        oldPwd : ev.target.value
                    },
                    success:function(result){
                        const checkResult =document.getElementById("oldPwd-area");
                        checkResult.style.display="block";
                        if(result==="NNNNN"){      //만약 비밀번호가 동일하지 않을 시
                            checkResult.style.color="red";
                            checkResult.innerHTML="*틀린 비밀번호입니다."
                        }else{                      //비밀번호가 동일할 시
                            checkResult.style.color="green";
                            checkResult.innerHTML="확인되었습니다."
                        }
                    },
                    error: function(){
                        console.log("비밀번호 체크 실패!");
                    }
                })
            },500)
        }else{
            document.getElementById("oldPwd-area").style.display = "none";
        }
    }
})



function editUser() {//빈칸 있을시 확인
    const checkResult =document.getElementById("oldPwd-area");

    let oldPwd = document.getElementById("oldPwd"); //현 비밀번호
    let userPwd = document.getElementById("userPwd");          //변경할 비밀번호
    let userPwdCheck = document.getElementById("userPwdCheck"); //변경된 비밀번호 확인
    let userNickName = document.getElementById("userNickName");
    let phone = document.getElementById("phone");
    let birth = document.getElementById("birth");
    let email = document.getElementById("email");
    let select_email = document.querySelector(".select-email");
    if(!userPwd.readOnly){
        //비밀번호 조건 부분-------------------------------------------
        if (oldPwd.value === "") { //나중에 loginUser 조건 비교 추가 (oldPwd.value ===loginUser.getPwd())
            alert("현재 비밀번호를 입력해주세요.");
            oldPwd.focus();
            return false;
        }

        else if (!isValidPassword(userPwd.value)) {
            alert("비밀번호 조건이 맞지않습니다.");
            userPwd.focus();
            return false;
        }

        else if (userPwd.value === "") {
            alert("변경할 비밀번호를 입력해주세요.");
            userPwd.focus();
            return false;
        }
        else if (userPwdCheck.value === "") {
            alert("비밀번호 확인을 입력해주세요.");
            userPwdCheck.focus();
            return false;
        }
        else if(checkResult.style.display="block" && userPwd.value ===oldPwd.value){
            alert("기존의 비밀번호와 동일합니다. 다시 입력해주세요.");
            return false;
        }
        else if (userPwd.value !== userPwdCheck.value) {
            alert("비밀번호가 동일하지않습니다. 다시 입력해주세요.");
            return false;
        }

    }
    //닉네임 조건 부분-----------------------------------------------
    if (userNickName.value === "") { //나중에 loginUser조건 비교 추가
        alert("닉네임을 입력해주세요.");
        userNickName.focus();
        return false;
    }
    if(!isVailidNickName(userNickName.value)){
        alert("부적절한 닉네임입니다. 다시 입력해주세요.");
        userNickName.focus();
        return false;
    }

    // 전화번호 조건 부분-------------------------------------------
    else if (phone.value === "") {
        alert("전화번호를 입력해주세요.");
        phone.focus();
        return false;
    }
        // else if (!isValidPhoneNumber(phone.value)) {
        //     alert("전화번호는 -제외 및 11자리 숫자로만 입력해주세요.");
        //     phone.focus();
        //     return false;
        // }

    //이메일 조건 부분-------------------------------------------
    else if (email.value === "") {
        alert("이메일을 입력해주세요.");
        email.focus();
        return false;
    }
    // else if (select_email.value === "" || !isVailidEmail(email.value)) {
    //     alert("도메인 혹은 이메일을 잘못입력하였습니다. ")
    //     email.focus();
    //     return false;
    // }

}



function onbcrt() { //현 비번과 변경할 비번이 동일한지 확인
    let oldPwd = document.getElementById("oldPwd");
    let userPwd = document.getElementById("userPwd");
    const checkResult =document.getElementById("oldPwd-area");
    let usealbePwd = document.querySelector(".usealbePwd");
    let cantPwdCheck = document.querySelector(".cantPwdCheck");
    let cantPwd = document.querySelector(".cantPwd");
    cantPwd.style.color = "red";

    if (oldPwd.value === userPwd.value) { //현재 비밀번호와 변경할 비밀번호가 같을 시
        if ((oldPwd.value !== "" && userPwd.value !== "") && checkResult.style.color==="green") {   //각 비밀번호가 동일하면 th안에 있는 "*사용할 수 없는 비밀번호입니다." 대신
            cantPwd.innerHTML = "*중복된 비번입니다";             //"중복된 비번입니다"로 변경 (단, 둘다 빈칸이 아니여야함)
            cantPwd.style.display = "block";
            return;
        }
    }
}

// 비밀번호 동일 여부 확인 + 유효가능 확인
function onb() { //onblur함수사용
    let oldPwd = document.getElementById("oldPwd");
    let userPwd = document.getElementById("userPwd");

    let usealbePwd = document.querySelector(".usealbePwd");
    let cantPwdCheck = document.querySelector(".cantPwdCheck");
    let cantPwd = document.querySelector(".cantPwd");

    if(userPwd.readOnly){ //비밀번호 변경 버튼을 클릭하지 않은 상태라면 div문 띄우지않기
        return;
    }

    //'변경할 비밀번호'가 정규식에 합당하지못하면 사용할 수 없는 비밀번호입니다.창 띄우기
    if (!isValidPassword(userPwd.value)) {
        cantPwd.style.display = "block";
        cantPwd.innerHTML = "사용할 수 없는 비밀번호입니다.";
        cantPwdCheck.style.display = "none";
        usealbePwd.style.display="none";
    }
    else { //정규식에 합당한 '변경할 비밀번호'가 '현재비밀번호'와 같으면 "중복된 비번입니다"를 보이게하기 (단, 둘다 빈칸이 아닐시에만)
        if (oldPwd.value === userPwd.value && (oldPwd.value !== "" || userPwd.value !== "")) {
            cantPwd.style.display = "block";
            usealbePwd.style.display="none";
        }
        else { //그렇지 않으면 none
            cantPwd.style.display = "none";
            usealbePwd.innerHTML="비밀번호가 동일합니다."
            usealbePwd.style.display="block";
        }
    }

    //새로운 비밀번호와 변경할 비밀번호가 동일하지 않을 경우
    if ((userPwd.value !== userPwdCheck.value) && cantPwd.style.display === "none") {
        cantPwdCheck.style.display = "block";          //*비밀번호가 일치하지 않습니다 (보이기)
        usealbePwd.style.display = "none";            // 비밀번호 동일합니다(가리기)
    } else if (userPwd.value === userPwdCheck.value && (userPwd.value !== "" && userPwdCheck.value !== "" && cantPwd.style.display === "none")) {
        //새로운 비밀번호와 변경할 비밀번호가 동일하고 둘다 빈킨이 아닐 경우
        usealbePwd.style.display = "block";
        cantPwdCheck.style.display = "none";
    }


}



//정규식 비밀번호조건 확인
function isValidPassword(password) {

    if (password.length < 8 || password.length > 20) {// 비밀번호의 길이가 8자 이상 20자 이하
        return false;
    }

    // 최소 한 개의 문자, 숫자, 특수문자가 포함
    let hasLetter = /[a-zA-Z]/.test(password);
    let hasNumber = /\d/.test(password);
    let hasSpecialChar = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(password);

    if (!hasLetter || !hasNumber || !hasSpecialChar) {
        return false;
    }

    return true;
}

//정규식 전화번호조건 확인
function isValidPhoneNumber(phoneNumber) {
    // 전화번호는 숫자로만 이루어져 있음
    if (!/^\d{11}$/.test(phoneNumber) || phoneNumber.includes("-")) {
        return false;
    }

    return true;
}

//정규식 이메일조건 확인
function isVailidEmail(email) {
    return /^[^\s!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|`~]*$/i.test(email);
}

//정규식 닉네임조건 확인
function isVailidNickName(nickname){
    if(!/^[\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F\uAC01-\uD7A3a-zA-Z]{0,16}$|^[^`~!@#$%^&*()_+={}\[\]|\\:;\"'<>,.?/]{0,8}$/.test(nickname)){
        return false;
    }
    return true;
}