package com.example.myblog.controller;

import com.example.myblog.message.ResponseFile;
import com.example.myblog.message.ResponseMessage;
import com.example.myblog.model.FileDB;
import com.example.myblog.service.FileDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api")
public class FileDBController {

    @Autowired
    private FileDBService fileDBService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage>
            uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            fileDBService.store(file);
            message = "Uploaded the file successfully : "
                    + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "could not upload the file : "
                    + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage(message));
        }
    }



    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]>
            getFile(@PathVariable String id) {
        FileDB fileDB = fileDBService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileDBService.getAllFile().map( dbFile -> {
            String fileDownloadURL = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadURL,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}












