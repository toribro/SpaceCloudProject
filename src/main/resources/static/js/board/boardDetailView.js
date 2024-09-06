window.onload = function(loginUser,boardNo) {
    selectReplyList(loginUser,boardNo);
    //setReplyCount();
    setInterval(selectReplyList, 2000);
}

function selectReplyList(loginUser,boardNo) {
    $.ajax({
        url: "rlist.bo",
        data: { boardNo: boardNo,
        success: function(res) {
            let str = "";
            for (let reply of res) {
                str += ("<div class='rlist-area'>" +
                    "<span class='rlist-writer'>" + reply.replyWriter + "</span>" +
                    "<span class='rlist-createDate'>" + reply.createDate + "</span>" +
                    (loginUser!= null && loginUser.admin == 'Y')?
                    "<span><button class='rlist-delete' style='text-decoration-line: none;' onclick='deleteReply(" + reply.replyNo + "," + reply.refBoardNo + ")'>삭제</button></span>" :
                    "" +
                    "<div class='rlist-content'>" + reply.replyContent + "</div>" +
                    "</div>");
            }
            document.querySelector("#reply-area > #reply-list").innerHTML = str;
            setReplyCount(res.length);
        }},
        error: function() {
            console.log("댓글 조회 중 오류 발생");
        }
    });
}

function setReplyCount(count) {
    document.querySelector("#reply-count").innerHTML = count;
}

function insertReply(bNo) {
    const boardNo = bNo;
    const content = document.querySelector("#reply-content").value;

    $.ajax({
        url: "rinsert.bo",
        data: { boardNo: boardNo, content: content },
        type: "POST",
        success: function(res) {
            document.querySelector("#reply-content").value = "";
            selectReplyList();
            setReplyCount();
        },
        error: function() {
            console.log("댓글 작성 중 오류 발생");
        }
    });
}

function deleteReply(replyNo, refBoardNo) {
    const confirmcheck = confirm("정말 삭제하시겠습니까?");
    if (confirmcheck) {
        location.href = "[[${@{/drlist.bo(num=${replyNo}, refNum=${refBoardNo})}}]]";
    } else {
        return false;
    }
}

function setReplyCount(){
    const rCount = document.querySelector("#reply-count");
}

