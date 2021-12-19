package com.praktika.MediaProject.Servise;

import com.praktika.MediaProject.Model.MediaFiles;
import com.praktika.MediaProject.Repository.MediaFilesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServise {

    private final MediaFilesRepo mediaFilesRepo;
    @Autowired
    public FileServise(MediaFilesRepo mediaFilesRepo) {this.mediaFilesRepo = mediaFilesRepo;}

    public MediaFiles findById(Integer id){

        return mediaFilesRepo.getById(id);
    }




    public List<MediaFiles> findAll(){
        return mediaFilesRepo.findAll();
    }
    public void deleteByID(Integer id){mediaFilesRepo.deleteById(id);}
}
