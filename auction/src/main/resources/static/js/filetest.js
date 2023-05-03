window.addEventListener("DOMContentLoaded", function() {
    CKEDITOR.replace("content", {
        height: 350

    });

    //   CKEDITOR.instances.content.insertHtml(imgTag);

    /** 이미지 추가 버튼 클릭 처리 S */
    const imageUploadEls = document.getElementsByClassName("imageUpload");
    for (const el of imageUploadEls) {
        el.addEventListener("click", function() {
            koreait.popup.open("/file/upload?image=true", "파일 업로드", 400, 500, true);
        });
    }

    /** 이미지 추가 버튼 클릭 처리 E */

});

/**
* 파일 업로드 후 처리
*
*
*/
function fileUploadCallback(files) {
    // 1. 팝업 닫기
    // 2. 파일 처리
    koreait.popup.close();

    if (files.length == 0) {
        return;
    }
    const attachedFilesEl = document.getElementById("attachedFiles");
    console.log(attachedFilesEl);
    const tpl = document.getElementById("fileTpl").innerHTML;
    const domParser = new DOMParser();
    let imageTags = "";
    for (const file of files) {
        imageTags += `<img src='${file.fileURL}'>`;

        let html = tpl;
        html = html.replace(/#{fileNo}/g, file.fileNo)
                    .replace(/#{fileName}/g, file.originalFilename);

        const dom = domParser.parseFromString(html, "text/html");
        const liEl = dom.querySelector("li");
        attachedFilesEl.appendChild(liEl);

        const removeEl = liEl.querySelector(".remove");
        removeEl.addEventListener("click", function() {
            alert("파일 삭제 구현해 보세요~!");
        });
    }

    CKEDITOR.instances.content.insertHtml(imageTags);
}