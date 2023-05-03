package org.koreait.controllers.files;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileInfoSaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file/upload")
@RequiredArgsConstructor
public class FileUploadController {

	private final FileInfoSaveService infoSaveService;

	@GetMapping
	public String upload(Model model) {
		model.addAttribute("addScript", new String[]{"fileManager"});
		System.out.println("업로드!");
		return "file/upload";
	}


	@ResponseBody
	@PostMapping
	public void uploadPs(MultipartFile[] files){
		/**
		 * 1. 파일 정보 저장
		 * 2. 파일 저장 처리 - getFilePath에 저장
		 */

		for (MultipartFile file : files) {
			// 1. 파일 정보 저장
			FileInfo fileInfo = infoSaveService.save(file);
			Long fileNo = fileInfo.getFileNo();

			// 2. 파일 업로드 경로
			String filePath = infoSaveService.getFilePath(fileNo);

			// 3. 파일 업로드 처리
			try {
				file.transferTo(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
