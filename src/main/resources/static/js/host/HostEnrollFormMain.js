function backPage() {
    location.href = "enrollPre.ho";
}

$(".input-host1").keyup(function(e) {
    content = $(this).val();
    $(".textCount1").text(content.length);
});
$(".input-host2").keyup(function(e) {
    content = $(this).val();
    $(".textCount2").text(content.length);
});
$(".input-host3").keyup(function(e) {
    content = $(this).val();
    $(".textCount3").text(content.length);
});
$(".input-host4").keyup(function(e) {
    content = $(this).val();
    $(".textCount4").text(content.length);
});

// 공간 태그 추가
function insertTag() {
    const tag = document.querySelector("#spaceTag").value;
    if (tag !== "") {
        $(".hidden-tag input").val($(".hidden-tag input").val() + '#' + tag + ' ');
        document.querySelector("#spaceTag").value = "";
        $(".hidden-tag").css('display', 'table-row');
    } else {
        alert("공간 태그를 입력해주세요!");
        $('#spaceTag').focus();
    }
}

// 공간 태그 삭제
function deleteTag() {
    $(".hidden-tag input").val('');
}

// 파일 첨부
function chooseFile(num) {
    const imgInput = document.querySelector("#file" + num);
    imgInput.click();
}

function clickSubmit() {
    let myform = document.querySelector("form");
    if(myform.requestSubmit) {
        myform.requestSubmit();
    } else {
        myform.submit();
    }
}
