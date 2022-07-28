package com.example.myblog.dao;

import com.example.myblog.model.FileDB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDBDao {

    FileDB findById(String id);

    List<FileDB> findAll();

    int save(FileDB fileDB);
}










