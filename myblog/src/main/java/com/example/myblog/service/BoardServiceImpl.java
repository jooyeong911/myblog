package com.example.myblog.service;

import com.example.myblog.dao.BoardDao;
import com.example.myblog.model.Board;
import com.example.myblog.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class BoardServiceImpl implements BoardService {


    @Autowired
    private BoardDao boardDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Optional<Board> findById(long idx) {
        return boardDao.findById(idx);
    }

    @Override
    public List<Board> findAll(Criteria criteria) {
        return boardDao.findAll(criteria);
    }

    @Override
    public List<Board> findByTitleContaining(Criteria criteria) {

        List<Board> boards = Collections.emptyList();

        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());

        int totalCount =
                boardDao.selectTotalCount(optionalCriteria.orElse(""));

        criteria.setTotalItems(totalCount);
        criteria.setTotalPages(totalCount / criteria.getSize());

        if (criteria.getTitle() == null)
            boards = boardDao.findAll(criteria);
        else
            boards = boardDao.findByTitleContaining(criteria);

        return boards;
    }

    @Override
    public Optional<Board> save(Board board) {
        long seqId = 0;

        if(board.getIdx() == null) {
            boardDao.insertBoard(board);
        }

        else {
            boardDao.updateBoard(board);
        }

        seqId = board.getIdx();
        logger.info("seqId {}", seqId);

        return boardDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long idx) {
        int queryResult = 0;

        queryResult = boardDao.deleteBoard(idx);

        return (queryResult >=1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;


        queryResult = boardDao.deleteAll();

        return (queryResult >=1) ? true : false;
    }
}











