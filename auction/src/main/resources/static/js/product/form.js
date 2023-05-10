window.addEventListener("DOMContentLoaded", function() {
	CKEDITOR.replace("description", {
		height: 350
	});


	/** 업로드버튼 클릭 시 수행 */
	const attachFiles = document.getElementsByClassName("attach_files");
	const fileEl = document.getElementById("file");
	const gid = document.getElementById("gid").value;

	for(const el of attachFiles){
		el.addEventListener("click", function() {
			const location = this.dataset.location;
			fileEl.fileLocation = location;
			fileEl.click();
		});
	}

	/** 파일이 등록된 경우 */
	fileEl.addEventListener("change", function(e) {
		const location = fileEl.fileLocation;
		const files = e.target.files;
		koreait.fileManager.uploads(files, gid, location, true);
		fileEl.value = "";
	});

});
	/** Callback을 통해 새로고침! */
	function fileUploadCallback(files){
		if(!files || files.length ==0){
			return;
		}

		const domParser = new DOMParser();
		const thumbs = document.getElementById("thumbs");
		const attachedFiles = document.getElementById("attached_files");
		const fileTpl = document.getElementById("file_tpl").innerHTML;
		const dd = thumbs.parentElement;
        const insert_ui = dd.lastElementChild;
		for(const file of files) {

			/** CKEditor에 파일 올리기 */
			if(file.location == 'editor') {
				const img = `<img src = '${file.fileURL}'>`;
				CKEDITOR.instances.description.insertHtml(img);

				let html = fileTpl;
				html = html.replace(/#\[fileNo\]/g, file.fileNo)
                           .replace(/#\[url\]/g, file.fileURL)
                           .replace(/#\[fileName\]/g, file.originalFilename);

				const dom = domParser.parseFromString(html, "text/html");
				const li = dom.querySelector("li");
				attachedFiles.appendChild(li);

				/** 본문에 이미지 올리기 */
				const insertEditor = li.querySelector(".insert_editor");
				insertEditor.addEventListener("click", function() {
					const url = this.dataset.url;
					const img = `<img src='${url}' />`;
					CKEDITOR.instances.description.insertHtml(img);
				});
			} else {	/** 상단 썸네일 */
				insert_ui.style.display = 'none';
				const photoTag = `<span class="thumb file_${file.fileNo}">
								<a href="/file/delete/${file.fileNo}" target="ifrmProcess" onclick="return confirm('정말 삭제하시겠습니까?');">
									<i class="remove xi-close-square-o"></i>
								</a>

								<span class='inner' style="background:url('${file.fileURL}'); background-size:cover;"></span>
								</span>`;

				const dom = domParser.parseFromString(photoTag, "text/html");
				const span = dom.querySelector("span");
				thumbs.appendChild(span);
				const inner = span.querySelector(".inner");
				inner.onclick = function() {
					const url = `/file/view/${file.fileNo}`;
					let w = 300, h = 300;
					const img = new Image();
					img.src = file.fileURL;
					img.onload = function() {
						if(img.width > 700) {
							w = 700;
							h = parseInt(img.width * w / img.height) + 150;
						}
						koreait.popup.open(url, "이미지 보기", w, h);
					};
				};
			}

		}
	}

	function fileDeleteCallback(fileNo) {
		if(!fileNo){
			return;
		}

		const fileEl = document.querySelector(`.file_${fileNo}`);

		const callback_btn = fileEl.parentElement.parentElement.lastElementChild;

		if (fileEl){
			console.dir(fileEl);
			console.dir(fileEl.parentElement);
			console.log(fileEl.parentElement.fileLocation);
			console.log(callback_btn);
			if(callback_btn.tagName='img'){
				callback_btn.style.display = 'block';
			}
			fileEl.parentElement.removeChild(fileEl);
		}
	}
