package com.example.myblog.service;
import com.example.myblog.model.Complain;
import com.example.myblog.paging.Criteria;

import java.util.List;
import java.util.Optional;


public interface ComplainService {

    Optional<Complain> findById(long id);


    List<Complain> findAll(Criteria criteria);

    List<Complain> findByTitleContaining(Criteria criteria);



    Optional<Complain> save(Complain complain);


    boolean deleteById(Long id);


    boolean deleteAll();
}











