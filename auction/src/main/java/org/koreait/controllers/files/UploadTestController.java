package org.koreait.controllers.files;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file/test")
public class UploadTestController {
    @GetMapping
    public String test(Model model) {
        model.addAttribute("addScript", new String[] { "ckeditor/ckeditor", "filetest" } );
        return "file/test";
    }
}
