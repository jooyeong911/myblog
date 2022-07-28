package com.example.myblog.dao;


import com.example.myblog.model.Board;
import com.example.myblog.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardDao {


    List<Board> findAll(Criteria criteria);

    List<Board> findByTitleContaining(Criteria criteria);

    int selectTotalCount(String title);

    Optional<Board> findById(Long idx);

    //    회원 생성 메소드
    long insertBoard(Board board);

    //    회원 수정 메소드
    long updateBoard(Board board);

    int deleteBoard(Long idx);

    int deleteAll();
}












