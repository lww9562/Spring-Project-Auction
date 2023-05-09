package org.koreait.controllers.files;

<<<<<<< HEAD:auction/src/main/java/org/koreait/controllers/files/FileUpload2Controller.java
=======
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileInfoSaveService;
import org.koreait.models.file.FileListService;
import org.koreait.repositories.FileInfoRepository;
>>>>>>> master:auction/src/main/java/org/koreait/controllers/files/FileUploadController.java
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
<<<<<<< HEAD:auction/src/main/java/org/koreait/controllers/files/FileUpload2Controller.java
@RequestMapping("/files/upload")
public class FileUpload2Controller {
    @GetMapping
    public String upload(Model model) {
        model.addAttribute("addScript", new String[]{ "fileManager" });
        return "files/form";
    }


    @ResponseBody
    @PostMapping
    public void uploadPs(MultipartFile[] files) {

    }
=======
@RequestMapping("/file/uploads")
@RequiredArgsConstructor
public class FileUploadController {

	private final FileInfoSaveService infoSaveService;
	private final FileListService listService;

	private final FileInfoRepository repository;

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
	public List<FileInfo> uploadPs(MultipartFile[] files, String gid, String location, boolean image){
		/**
		 * 1. 파일 정보 저장
		 * 2. 파일 저장 처리 - getFilePath에 저장
		 */

		for (MultipartFile file : files) {
			String contentType = file.getContentType();
			// 이미지만 업로드 제한된 경우 이미지가 아닌 경우 업로드 제외
			if (image && contentType.indexOf("image") == -1) {
				continue;
			}

			// 1. 파일 정보 저장
			FileInfo fileInfo = infoSaveService.save(file, gid, location);
			Long fileNo = fileInfo.getFileNo();

			gid = gid == null ? fileInfo.getGid() : gid;

			// 2. 파일 업로드 경로
			String filePath = infoSaveService.getFilePath(fileNo);
			String fileUrl = infoSaveService.getFileURL(fileNo);
			System.out.println("----------------------------------");
			System.out.println(fileInfo);
			fileInfo.setFilePath(filePath);
			fileInfo.setFileURL(fileUrl);
			System.out.println(fileInfo);

			// 3. 파일 업로드 처리
			try {
				file.transferTo(new File(filePath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		List<FileInfo> fileInfos = listService.getAll(gid, location);
		fileInfos.stream().forEach(System.out::println);

		repository.saveAllAndFlush(fileInfos);
		return fileInfos;
	}
>>>>>>> master:auction/src/main/java/org/koreait/controllers/files/FileUploadController.java
}
