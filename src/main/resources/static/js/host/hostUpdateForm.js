$(function(lists) {
    let list = lists

    // 첨부 이미지 리스트에 따라 이미지를 화면에 출력
    for (let i = 0; i < list.length; i++) {
        $("#content-img" + (i + 1)).attr("src", "/resources/" + list[i].filePath + list[i].changeName);
    }
});

// 주소 검색 (Daum API 사용)
function searchAddr() {
    new daum.Postcode({
        oncomplete: function(data) {
            $('#spaceAddress').val(data.address);  // 주소 입력 필드에 결과 삽입
        }
    }).open();
}

// 파일 첨부 시 이미지 미리보기 처리
function loadImg(inputFile, num) {
    if (inputFile.files.length == 1) {
        const reader = new FileReader();
        reader.readAsDataURL(inputFile.files[0]);
        reader.onload = function(ev) {
            switch(num) {
                case 1:
                    document.getElementById("title-img").src = ev.target.result;
                    $('.main-img').css('display', 'none');
                    $('.main-img-hidden').css('display', 'table-row');
                    break;
                case 2:
                    $("#content-img1").attr("src", ev.target.result);
                    break;
                case 3:
                    $("#content-img2").attr("src", ev.target.result);
                    break;
                case 4:
                    $("#content-img3").attr("src", ev.target.result);
                    break;
                case 5:
                    $("#content-img4").attr("src", ev.target.result);
                    break;
                case 6:
                    $("#content-img5").attr("src", ev.target.result);
                    break;
            }
        }
    }
}

// 파일 선택 함수
function chooseFile(num) {
    const imgInput = document.querySelector("#file" + num);
    imgInput.click();
}

// 태그 추가
function insertTag() {
    const tag = document.querySelector("#spaceTag").value;
    if (tag !== "") {
        $(".hidden-tag input").val($(".hidden-tag input").val() + '#' + tag + ' ');
        document.querySelector("#spaceTag").value = "";
        $(".hidden-tag").css('display', 'table-row');

        let count = document.querySelector(".hidden-tag input").value.split('#').length - 1;
        if (count >= 5) {
            $(".tag-btn").attr("disabled", true);
            $(".tag-btn").css("background-color", "gray");
        }
    } else {
        alert("공간 태그를 입력해주세요!");
        $('#spaceTag').focus();
    }
}

// 태그 삭제
function deleteTag() {
    $(".hidden-tag input").val('');
    $(".tag-btn").attr("disabled", false);
    $(".tag-btn").css("background-color", "#704DE4");
}

// 시설 안내 추가
let spaceInfoCheck = 1;
function insertSpaceInfo() {
    const info = document.querySelector("#spaceInformation-input").value;
    if (info !== "") {
        $(".hidden-spaceinfo").css('display', 'table-row');
        $(".hidden-spaceinfo-btn").css('display', 'table-row');

        let inputElement = document.createElement('input');
        inputElement.value = spaceInfoCheck + ". " + info;
        inputElement.classList.add("input-text");
        inputElement.name = "spaceInformation";
        inputElement.readOnly = true;
        document.querySelector('.hidden-spaceinfo').appendChild(inputElement);
        document.querySelector("#spaceInformation-input").value = "";

        spaceInfoCheck++;
        if (spaceInfoCheck == 11) {
            $(".spaceInfo-btn").attr("disabled", true);
            $(".spaceInfo-btn").css("background-color", "gray");
        }
    } else {
        alert("시설 안내를 입력해주세요!");
        $('#spaceInformation-input').focus();
    }
}

// 시설 안내 삭제
function deleteSpaceInfo() {
    $('.hidden-spaceinfo').children().remove();
    $(".hidden-spaceinfo-btn").css('display', 'none');
    $(".spaceInfo-btn").attr("disabled", false);
    $(".spaceInfo-btn").css("background-color", "#704DE4");
    spaceInfoCheck = 1;
}

// 주의사항 추가
let spaceCautionCheck = 1;
function insertCaution() {
    const caution = document.querySelector("#spaceCaution-input").value;
    if (caution !== "") {
        $(".hidden-caution").css('display', 'table-row');
        $(".hidden-caution-btn").css('display', 'table-row');

        let inputElement = document.createElement('input');
        inputElement.value = spaceCautionCheck + ". " + caution;
        inputElement.classList.add("input-text");
        inputElement.name = "spaceCaution";
        inputElement.readOnly = true;
        document.querySelector('.hidden-caution').appendChild(inputElement);
        document.querySelector("#spaceCaution-input").value = "";

        spaceCautionCheck++;
        if (spaceCautionCheck == 11) {
            $(".caution-btn").attr("disabled", true);
            $(".caution-btn").css("background-color", "gray");
        }
    } else {
        alert("주의 사항을 입력해주세요!");
        $('#spaceCaution-input').focus();
    }
}

// 주의사항 삭제
function deleteCaution() {
    $('.hidden-caution').children().remove();
    $(".hidden-caution-btn").css('display', 'none');
    $(".caution-btn").attr("disabled", false);
    $(".caution-btn").css("background-color", "#704DE4");
    spaceCautionCheck = 1;
}

// 글자 수 카운트 업데이트
function updateCharCount(elementClass, countClass) {
    $(elementClass).keyup(function(e) {
        let content = $(this).val();
        $(countClass).text(content.length);
    });
}

// 글자 수 카운트 호출
updateCharCount(".input-host1", ".textCount1");
updateCharCount(".input-host2", ".textCount2");
updateCharCount(".input-host3", ".textCount3");
updateCharCount(".input-host4", ".textCount4");

// 제출 버튼 클릭 시 폼 제출
function clickSubmit() {
    let myForm = document.querySelector("form");
    if (myForm.requestSubmit) {
        myForm.requestSubmit();
    } else {
        myForm.submit();
    }
}

// 엔터키 입력 시 자동 추가 기능
$('#spaceTag').keypress(function(event) {
    if (event.which === 13) {
        $('.tag-btn').click();
        $('#spaceTag').focus();
    }
});

$('#spaceInformation-input').keypress(function(event) {
    if (event.which === 13) {
        $('.spaceInfo-btn').click();
    }
});

$('#spaceCaution-input').keypress(function(event) {
    if (event.which === 13) {
        $('.caution-btn').click();
    }
});
