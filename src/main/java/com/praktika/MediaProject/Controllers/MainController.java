package com.praktika.MediaProject.Controllers;

import com.praktika.MediaProject.Model.MediaFiles;
import com.praktika.MediaProject.Repository.MediaFilesRepo;
import com.praktika.MediaProject.Servise.FileServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


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





//    @GetMapping("file-delete/{id}")
//    public String deleteFile(@PathVariable("id") Integer id){
//        fileServise.deleteByID(id);
//        return "redirect:/media";
//    }
}
