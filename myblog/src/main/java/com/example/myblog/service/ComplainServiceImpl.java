package com.example.myblog.service;

import com.example.myblog.dao.ComplainDao;
import com.example.myblog.model.Complain;
import com.example.myblog.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// @Service : 스프링에 객체로 생성
@Service
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainDao complainDao;


    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Optional<Complain> findById(long id) {
        return complainDao.findById(id);
    }


    @Override
    public List<Complain> findAll(Criteria criteria) {
        return complainDao.findAll(criteria);
    }

    @Override
    public List<Complain> findByTitleContaining(Criteria criteria) {

        List<Complain> complains = Collections.emptyList();

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());


        int totalCount =
                complainDao.selectTotalCount(optionalCriteria.orElse(""));


        criteria.setTotalItems(totalCount);
        criteria.setTotalPages(totalCount / criteria.getSize());


        if (criteria.getTitle() == null)
            complains = complainDao.findAll(criteria);
        else
            complains = complainDao.findByTitleContaining(criteria);

        return complains;
    }


    @Override
    public Optional<Complain> save(Complain complain) {

        long seqId = 0;


        logger.info("complain {}", complain);


        if(complain.getId() == null) {
            complainDao.insertComplain(complain);
        }

        else {
            complainDao.updateComplain(complain);
        }


        seqId = complain.getId();
        logger.info("seqId {}", seqId);

        return complainDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = complainDao.deleteComplain(id);

        return (queryResult >=1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;



        queryResult = complainDao.deleteAll();

        return (queryResult >=1) ? true : false;
    }
}











