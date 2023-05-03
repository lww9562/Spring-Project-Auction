var koreait = koreait || {};
koreait.fileManager = {
	/*
	* 파일 업로드 처리
	*
	*
	*/
	uploads(files, gid, location) {
		try {
			if(!files || files.length == 0){
				throw new Error("업로드할 파일을 선택하세요.");
			}

            console.log(files);
            return;

            const { header, token } = commonLib.getCsrfToken();

            const formData = new FormData();
            for (const file of files) {
                formData.append("files", file);
            }

            // gid(그룹 ID)가 있으면 추가
            if (gid) {
                formData.append("gid", gid);
            }

            // location이 있으면 추가
            if (location) {
                formData.append("location", location);
            }

            const xhr = new XMLHttpRequest();
            xhr.open("POST", "/file/upload");
            xhr.setRequestHeader(header, token);
            xhr.send(formData);
            xhr.responseType="json";
            xhr.onreadystatechange = function() {
                if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
                    console.log(xhr.response);
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
                const gid = fileEl.dataset.gid;
                const location = fileEl.dataset.location;

            	const files = fileEl.files;
            	koreait.fileManager.uploads(files, gid, location);
            }
		});


	}

//파일 선택 이벤트 처리 E
})