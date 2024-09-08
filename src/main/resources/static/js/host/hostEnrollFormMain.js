$(function() {
    // 공간명, 한 줄 소개, 공간 소개, 위치 정보 입력 시 글자 수 카운트
    $(".input-host1").keyup(function() {
        content = $(this).val();
        $(".textCount1").text(content.length);
    });
    $(".input-host2").keyup(function() {
        content = $(this).val();
        $(".textCount2").text(content.length);
    });
    $(".input-host3").keyup(function() {
        content = $(this).val();
        $(".textCount3").text(content.length);
    });
    $(".input-host4").keyup(function() {
        content = $(this).val();
        $(".textCount4").text(content.length);
    });


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

    // 공간 유형 선택 복원
    const spaceKind = "${spaceKind}";
    const inputArr = document.querySelectorAll("input[name=spaceKind]");
    for (let input of inputArr) {
        if (spaceKind.includes(input.value)){
            input.checked = true;
        }
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

// 태그 입력 및 삭제
function insertTag() {
    const tag = document.querySelector("#spaceTag").value;

    if (!validateInput1(tag)){
        alert("공간 태그에 # 은 입력할 수 없습니다.");
        $('#spaceTag').focus();
    } else if (tag !== "") {
        $(".hidden-tag input").val($(".hidden-tag input").val()+'#'+tag+' ');
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

function deleteTag() {
    $(".hidden-tag input").val('');
    $(".tag-btn").attr("disabled", false);
    $(".tag-btn").css("background-color", "#704DE4");
}

// 시설 안내 입력 및 삭제
let spaceInfoCheck = 1;
function insertSpaceInfo() {
    const tag = document.querySelector("#spaceInformation-input").value;

    if (!validateInput2(tag)) {
        alert("시설 안내에 / 은 입력할 수 없습니다.");
        $('#spaceInformation-input').focus();
    } else if (tag !== "") {
        $(".hidden-spaceinfo").css('display', 'table-row');
        $(".hidden-spaceinfo-btn").css('display', 'table-row');

        let inputElement = document.createElement('input');
        inputElement.value = spaceInfoCheck + ". " + tag;
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

function deleteSpaceInfo() {
    $('.hidden-spaceinfo').children().remove();
    $(".hidden-spaceinfo-btn").css('display', 'none');
    $(".spaceInfo-btn").attr("disabled", false);
    $(".spaceInfo-btn").css("background-color", "#704DE4");
    spaceInfoCheck = 1;
}

// 주의사항 입력 및 삭제
let spaceCaution = 1;
function insertCaution() {
    const tag = document.querySelector("#spaceCaution-input").value;
    if (!validateInput2(tag)) {
        alert("주의 사항에 / 은 입력할 수 없습니다.");
        $('#spaceCaution-input').focus();
    } else if (tag !== "") {
        $(".hidden-caution").css('display', 'table-row');
        $(".hidden-caution-btn").css('display', 'table-row');

        let inputElement = document.createElement('input');
        inputElement.value = spaceCaution + ". " + tag;
        inputElement.classList.add("input-text");
        inputElement.name = "spaceCaution";
        inputElement.readOnly = true;
        document.querySelector('.hidden-caution').appendChild(inputElement);
        document.querySelector("#spaceCaution-input").value = "";
        spaceCaution++;
    } else {
        alert("주의 사항을 입력해주세요!");
        $('#spaceCaution-input').focus();
    }

    if (spaceCaution == 11) {
        $(".caution-btn").attr("disabled", true);
        $(".caution-btn").css("background-color", "gray");
    }
}

function deleteCaution() {
    $('.hidden-caution').children().remove();
    $(".hidden-caution-btn").css('display', 'none');
    $(".caution-btn").attr("disabled", false);
    $(".caution-btn").css("background-color", "#704DE4");
    spaceCaution = 1;
}

// 이미지 처리
function loadImg(inputFile, num) {
    if (inputFile.files.length === 1) {
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
                    document.querySelector("#content-img1").src = ev.target.result;
                    $('.detail-img').css('display', 'none');
                    $('.detail-img-hidden').css('display', 'table-row');
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

function chooseFile(num) {
    const imgInput = document.querySelector("#file" + num);
    imgInput.click();
}


function clickSubmit() {
    let myform = document.querySelector("form");
    if (myform.requestSubmit) {
        myform.requestSubmit();
    } else {
        myform.submit();
    }
}

// 입력 유효성 검사
function validateInput1(inputString) {
    var pattern = /#/;
    return !pattern.test(inputString);
}

function validateInput2(inputString) {
    var pattern = /\//;
    return !pattern.test(inputString);
}
