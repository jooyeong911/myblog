package com.example.myblog.dao;


import com.example.myblog.model.Complain;
import com.example.myblog.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface ComplainDao {


    List<Complain> findAll(Criteria criteria);

    List<Complain> findByTitleContaining(Criteria criteria);

    int selectTotalCount(String title);

    Optional<Complain> findById(Long id);

    long insertComplain(Complain complain);

    long updateComplain(Complain complain);

    int deleteComplain(Long id);

    int deleteAll();
}












