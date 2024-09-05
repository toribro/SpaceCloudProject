$(function() {
    $("#table > tbody > tr").click(function() {
        const boardNo = $(this).children().eq(0).text();
        location.href = "/detail.bo?num=" + boardNo;
    });
});
