package com.praktika.MediaProject.Controllers;

import com.praktika.MediaProject.Repository.MediaFilesRepo;
import com.praktika.MediaProject.Servise.FileServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @Autowired
    private MediaFilesRepo mediaFilesRepo;

    @Value("${upload.path}")
    private String pathFile;

    private final FileServise fileServise;

    @Autowired
    public MainController(FileServise fileServise) {
        this.fileServise = fileServise;

    }

    @GetMapping("/")
    public String findAll(Model model) {


        return "redirect:/media";
    }

}
