
function clearForm(e) {
	$("#preview").remove();
}

$(function () {
	$("#input_imgs").on("change", handleImgsFilesSelect);
});

	// e : change 이벤트를 받음
	function handleImgsFilesSelect (e) {
		// 이벤트가 일어난 파일 객체의 이미지 파일들을 가져옴
		var files = e.target.files;

		// 파일들을 배열로 만들어 관리
		var filesArr = Array.prototype.slice.call(files);

		// f : 각각의 파일 객체
		filesArr.forEach(function (f) {
			if (!f.type.match("image.*")) {
				alert("이미지만 가능합니다.");

				// 업로드 종료(실패)
				return;
			}

			// 각 이미지를 reader로 읽어들임
			var reader = new FileReader();
			reader.onload = function (e) {
				var img_html = "<img src=\"" + e.target.result + "\" style='width: 500px;' id='preview' />";
				$(".imgs_wrap").append(img_html);
			}
			reader.readAsDataURL(f);
		});  // end forEach
	}
