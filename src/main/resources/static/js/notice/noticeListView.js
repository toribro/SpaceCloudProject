document.addEventListener("DOMContentLoaded", function () {
    const rows = document.querySelectorAll("#table > tbody > tr");

    rows.forEach((row) => {
        row.addEventListener("click", function () {
            const noticeNo = this.children[0].innerText;
            location.href = `/detail.no?num=${noticeNo}`;
        });
    });
});
