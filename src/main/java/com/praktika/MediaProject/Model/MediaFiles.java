package com.praktika.MediaProject.Model;

import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "media_files")
public class MediaFiles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Integer idFile;

    private String FileName;

    private Date UploadDate;

    private String Path;

    public MediaFiles() {
    }

    public MediaFiles(String fileName, Date uploadDate, String path) {
        FileName = fileName;
        UploadDate = uploadDate;
        Path = path;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Date getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        UploadDate = uploadDate;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
