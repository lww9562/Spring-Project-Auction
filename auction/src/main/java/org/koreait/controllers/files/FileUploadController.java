package org.koreait.controllers.files;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileInfoSaveService;
import org.koreait.models.file.FileListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file/upload")
@RequiredArgsConstructor
public class FileUploadController {

	private final FileInfoSaveService infoSaveService;
	private final FileListService listService;

	@GetMapping
	public String upload(String gid, String location, boolean image, Model model) {
		model.addAttribute("addScript", new String[]{"fileManager"});
		model.addAttribute("gid", gid);
		model.addAttribute("location", location);
		model.addAttribute("imageOnly", image);

		return "file/upload";
	}


	@ResponseBody
	@PostMapping
	public List<FileInfo> uploadPs(MultipartFile[] files, String gid, String location){
		/**
		 * 1. 파일 정보 저장
		 * 2. 파일 저장 처리 - getFilePath에 저장
		 */

		for (MultipartFile file : files) {
			// 1. 파일 정보 저장
			FileInfo fileInfo = infoSaveService.save(file, gid, location);
			Long fileNo = fileInfo.getFileNo();

			gid = gid == null ? fileInfo.getGid() : gid;

			// 2. 파일 업로드 경로
			String filePath = infoSaveService.getFilePath(fileNo);

			// 3. 파일 업로드 처리
			try {
				file.transferTo(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		List<FileInfo> fileInfos = listService.getAll(gid, location);
		fileInfos.stream().forEach(System.out::println);
		return fileInfos;
	}
}
