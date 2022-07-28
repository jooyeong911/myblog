package com.example.myblog.service;

import com.example.myblog.dao.FileDBDao;
import com.example.myblog.model.FileDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileDBService {


    @Autowired
    private FileDBDao fileDBDao;

//    로그 클래스 객체 생성
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public int store(MultipartFile file) throws IOException {


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB fileDB = new
                FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBDao.save(fileDB);
    }

    public FileDB getFile(String id) {
        return fileDBDao.findById(id);
    }

    public Stream<FileDB> getAllFile() {
        Stream<FileDB> resFileDB = fileDBDao.findAll().stream();

        return resFileDB;
    }
}









