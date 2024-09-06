function testConfirm() {
    if (!document.getElementById("cbtest-19").checked) {
        alert("탈퇴에 동의해주시기 바랍니다.");
        return false;
    } else {
        return confirm("정말로 탈퇴하시겠습니까?");
    }
}
