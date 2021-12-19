package com.praktika.MediaProject.Controllers;

import com.praktika.MediaProject.Model.MediaFiles;
import com.praktika.MediaProject.Repository.MediaFilesRepo;
import com.praktika.MediaProject.Servise.FileServise;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class FileController {
    @Autowired
    private final MediaFilesRepo mediaFilesRepo;

    private final FileServise fileServise;

    @Value("${upload.path}")
    private String pathFile;

    @Autowired
    public FileController(MediaFilesRepo mediaFilesRepo, FileServise fileServise) {

        this.mediaFilesRepo = mediaFilesRepo;
        this.fileServise = fileServise;
    }

    @GetMapping("/media")
    public String GetAllUsers(Model model) {
//        return ResponseEntity.ok(this.mediaFilesRepo.findAll());

        Iterable<MediaFiles> mediafiles = mediaFilesRepo.findAll();
        model.addAttribute("media", mediafiles);

        List<MediaFiles> rows = fileServise.findAll();
        model.addAttribute("files", rows);



        return "media";
    }

    @PostMapping("/media")
    public String add(@RequestParam Map<String ,Object> model, @RequestParam("file") MultipartFile file) throws IOException {
        MediaFiles mediaFiles = new MediaFiles();
        if(file != null)
        {
            File uploadDir = new File(pathFile);
            if(!uploadDir.exists())
            {
                uploadDir.mkdir();
            }
//        String uuidFile = UUID.randomUUID().toString();
//        String resultFileName = uuidFile + "." + file.getOriginalFilename();

            String resultFileName = file.getOriginalFilename();

            file.transferTo(new File(pathFile + "/"+ resultFileName));


            mediaFiles.setFileName(resultFileName);
            Date date = new Date();
            mediaFiles.setUploadDate(date);
            mediaFiles.setPath(pathFile + "/" + resultFileName);

            mediaFilesRepo.save(mediaFiles);

        }
        return "redirect:/media";
    }

    @GetMapping("file-delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id){
        fileServise.deleteByID(id);
        return "redirect:/media";
    }

    @GetMapping("file-view/{name}+{id}")
    public String viewFile(@PathVariable("name") String name, @PathVariable("id") Integer id ,Model model){

        model.addAttribute("fname",name);
        Iterable<MediaFiles> mediafiles = mediaFilesRepo.findAll();
        String fpath = pathFile + "/"+name;


        model.addAttribute("directory", fpath);
        model.addAttribute("media",mediafiles);
        return "fileview";
    }

//    @GetMapping("file-download/{id}")
//    public String downloadFile(@PathVariable("id") Integer id){
//        fileServise.deleteByID(id);
//        return "redirect:/media";
//    }


}
