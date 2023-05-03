package org.koreait.controllers.files;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
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
}
