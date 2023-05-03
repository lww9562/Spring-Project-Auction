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

            const { header, token } = commonLib.getCsrfToken();

            const formData = new FormData();
            for (const file of files) {
                formData.append("files", file);
            }

            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/file/upload");
            xhr.setRequestHeader(header, token);
            xhr.send(formData);

            xhr.onreadystatechange = function() {
                if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
                    console.log(xhr.reponseText);
                }
            };

		} catch (err) {
			alert(err.message);
		}
	}
};



window.addEventListener("DOMContentLoaded", function() {
//파일 선택 이벤트 처리 S
	const fileUploadEls = document.getElementsByClassName("fileUpload");

	for(const el of fileUploadEls){
		el.addEventListener("click", function() {
			const dataset = this.dataset;
            const fileId = dataset.targetId;

            const fileEl = document.getElementById(fileId);
            if(fileEl) {
            	const files = fileEl.files;
            	koreait.fileManager.uploads(files);
            }
		});


	}

//파일 선택 이벤트 처리 E
})