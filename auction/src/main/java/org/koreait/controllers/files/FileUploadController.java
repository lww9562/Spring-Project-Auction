package org.koreait.controllers.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/upload")
public class FileUploadController {
	@GetMapping
	public String upload(Model model) {
		model.addAttribute("addScript", new String[]{"fileManager"});
		return "file/upload";
	}



	@ResponseBody
	@PostMapping
	public void uploadPs(MultipartFile[] files){

	}
}
