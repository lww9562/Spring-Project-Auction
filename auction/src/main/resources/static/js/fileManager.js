var koreait = koreait || {};
koreait.fileManager = {
	/*
	* 파일 업로드 처리
	*
	*
	*/
	uploads(files, gid, location, imageOnly) {
		try {
			if(!files || files.length == 0){
				throw new Error("업로드할 파일을 선택하세요.");
			}

            const { header, token } = commonLib.getCsrfToken();
            const formData = new FormData();

            /** 업로드 파일 이미지 형식 체크 S */
            if (imageOnly) { // 이미지가 아닌 파일이 섞여 있으면 X
                formData.append("image", true);

                for (const file of files) {
                    if (file.type.indexOf("image") == -1) { // 이미지가 아닌 형식
                       throw new Error("이미지 형식의 파일만 업로드 하세요.");
                    }
                }
            }
            /** 업로드 파일 이미지 형식 체크 E */


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
                    if (typeof parent.fileUploadCallback == 'function') {
                        parent.fileUploadCallback(xhr.response);
                    }

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
                const imageOnly = fileEl.dataset.imageOnly;

            	const files = fileEl.files;
            	koreait.fileManager.uploads(files, gid, location, imageOnly == 'true');
            }
		});


	}

//파일 선택 이벤트 처리 E
})