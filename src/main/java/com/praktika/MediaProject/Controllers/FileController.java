package com.praktika.MediaProject.Controllers;

import com.praktika.MediaProject.Model.MediaFiles;
import com.praktika.MediaProject.Repository.MediaFilesRepo;
import com.praktika.MediaProject.Servise.FileServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


        Iterable<MediaFiles> mediafiles = mediaFilesRepo.findAll();
        model.addAttribute("media", mediafiles);

        List<MediaFiles> rows = fileServise.findAll();
        model.addAttribute("files", rows);


        return "media";
    }

    @PostMapping("/media")
    public String add(@RequestParam Map<String, Object> model, @RequestParam("file") MultipartFile file) throws IOException {
        MediaFiles mediaFiles = new MediaFiles();
        if (file != null) {
            File uploadDir = new File(pathFile);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }


            String resultFileName = file.getOriginalFilename();

            file.transferTo(new File(pathFile + "/" + resultFileName));


            mediaFiles.setFileName(resultFileName);
            Date date = new Date();
            mediaFiles.setUploadDate(date);
            mediaFiles.setPath(pathFile + "/" + resultFileName);

            mediaFilesRepo.save(mediaFiles);

        }
        return "redirect:/media";
    }

    @GetMapping("file-delete/{id}")
    public String deleteFile(@PathVariable("id") Integer id) {
        fileServise.deleteByID(id);
        return "redirect:/media";
    }

    //Alt. view.
//    @GetMapping("file-view/{name}+{id}")
//    public String viewFile(@PathVariable("name") String name, @PathVariable("id") Integer id ,Model model){
//
//        String fpath = "/UploadFiles" + "/" + name;
//        model.addAttribute("directory", fpath);
//
//        return "fileview";
//    }


    @GetMapping("file-download/{name}-download")
    public void downloadFile(HttpServletResponse resonse, @PathVariable("name") String fileName) throws IOException {


        File file = new File(pathFile + "/" + fileName);


        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        resonse.setContentLength((int) file.length());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }




    @GetMapping("file-view/{name}+{id}")
    public void viewFile(HttpServletResponse resonse, @PathVariable("name") String fileName, @PathVariable("id") Integer id) throws IOException {


        File file = new File(pathFile + "/" + fileName);


        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }

}





