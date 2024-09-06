document.addEventListener("DOMContentLoaded", function () {
    selectReplyList();
    setReplyCount();
    setInterval(selectReplyList, 2000);

    document.querySelector("#cancelButton").addEventListener("click", function () {
        const confirmCheck = confirm("정말 삭제하시겠습니까?");
        if (confirmCheck) {
            location.href = `/delete.no?num=${document.querySelector("#noticeNo").value}`;
        }
    });
});

function selectReplyList() {
    const noticeNo = document.querySelector("#noticeNo").value;

    $.ajax({
        url: "/rlist.no",
        data: { noticeNo: noticeNo },
        success: function (res) {
            let str = "";
            for (let reply of res) {
                str += `<div class='rlist-area'>
                            <span class='rlist-writer'>${reply.replyWriter}</span>
                            <span class='rlist-createDate'>${reply.createDate}</span>
                            <div class='rlist-content'>${reply.replyContent}</div>
                        </div>`;
            }
            document.querySelector("#reply-list").innerHTML = str;
            setReplyCount(res.length);
        },
        error: function () {
            console.error("댓글 조회중 오류 발생");
        }
    });
}

function setReplyCount(count) {
    document.querySelector("#reply-count").innerText = count;
}

function insertReply() {
    const noticeNo = document.querySelector("#noticeNo").value;
    const content = document.querySelector("#reply-content").value;

    $.ajax({
        url: "/rinsert.no",
        type: "POST",
        data: { noticeNo: noticeNo, content: content },
        success: function () {
            document.querySelector("#reply-content").value = "";
            selectReplyList();
        },
        error: function () {
            console.error("댓글 작성중 오류 발생");
        }
    });
}
