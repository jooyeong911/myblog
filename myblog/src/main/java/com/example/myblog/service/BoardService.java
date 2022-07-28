package com.example.myblog.service;

import com.example.myblog.model.Board;
import com.example.myblog.paging.Criteria;

import java.util.List;
import java.util.Optional;


public interface BoardService {

    Optional<Board> findById(long idx);


    List<Board> findAll(Criteria criteria);

    List<Board> findByTitleContaining(Criteria criteria);


    Optional<Board> save(Board board);


    boolean deleteById(Long idx);


    boolean deleteAll();
}











