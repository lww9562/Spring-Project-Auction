package org.koreait.controllers.files;

import lombok.RequiredArgsConstructor;
import org.koreait.models.file.FileDeleteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file/delete")
@RequiredArgsConstructor
public class FileDeleteController {
	private final FileDeleteService deleteService;

	@RequestMapping("/{fileNo}")
	public String delete(@PathVariable Long fileNo, Model model) {
		String script = null;

		try{
			deleteService.delete(fileNo);

			System.out.println("===================file-delete===================");

			script = String.format("if (typeof parent.fileDeleteCallback == 'function') parent.fileDeleteCallback(%d);", fileNo);

		} catch(Exception e){
			e.printStackTrace();
			script = String.format("alert('%s');", e.getMessage());
		}
		model.addAttribute("script", script);
		return "commons/execute_script";
	}

}
