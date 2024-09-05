function cancelConfirm(reservationNum, spaceNo) {
    let confirmCancel = confirm("취소하시겠습니까?");
    if (confirmCancel) {
        location.href = '/cancel.re?reservationNo=' + reservationNum + '&spaceNo=' + spaceNo;
    } else {
        return false;
    }
}
