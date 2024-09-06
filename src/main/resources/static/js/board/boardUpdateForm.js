function loadImg(inputFile) {
    if (inputFile.files.length == 1) {
        const reader = new FileReader();
        reader.readAsDataURL(inputFile.files[0]);
        reader.onload = function (ev) {
            document.getElementById("content-img").src = ev.target.result;
        };
    } else {
        document.getElementById("content-img").src = null;
    }
}

function chooseFile() {
    const imgInput = document.querySelector("#file");
    imgInput.click();
}
