var koreait = koreait || {};
koreait.fileManager = {
	/*
	* 파일 업로드 처리
	*
	*/
	uploads(files) {
		try {
			if(!files || files.length == 0){
				throw new Error("업로드할 파일을 선택하세요.");
			}

		} catch (err) {
			alert(err.message);
		}
	}
};



window.addEventListener("DOMContentLoaded", function() {

/** 파일 선택 이벤트 처리 S */
const fileUploadEls = document.getElementsByClassName("fileUpload");
for (const el of fileUploadEls) {
    el.addEventListener("click", function() {
        const dataset = this.dataset;
        const fileId = dataset.targetId;

        const fileEl = document.getElementById(fileId);
        if (fileEl) {
            const files = fileEl.files;
            koreait.fileManager.uploads(files);
        }
    });
}
/** 파일 선택 이벤트 처리 E */

});
