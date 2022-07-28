package com.example.myblog.controller;

import com.example.myblog.model.Board;
import com.example.myblog.paging.Criteria;
import com.example.myblog.service.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/boards")
    public ResponseEntity<Map<String, Object>>
    getAllTitlePage(Criteria criteria) {
        List<Board> boards = boardService.findByTitleContaining(criteria);

        try {
            if (boards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


//			Todo : Map에 넣어 객체 + 페이지 정보를 Vue 로 전송
            Map<String, Object> response = new HashMap<>();
            response.put("boards", boards);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/boards")
    public ResponseEntity<Object>
    createBoard(@RequestBody Board board) {

        Board savedBoard = boardService.save(board).get();
        try {
            return new ResponseEntity<Object>(savedBoard, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/boards/{idx}")
    public ResponseEntity<Object>
    getBoardById(@PathVariable("idx") Long idx) {
        Board board = boardService.findById(idx).get();

        try {
            if (board != null) {
                return new ResponseEntity<Object>(board, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/boards/{idx}")
    public ResponseEntity<Object> updateBoard(
            @PathVariable("idx") Long idx,
            @RequestBody Board board
    ) {
        try {
            board.setIdx(idx);
            Board savedBoard = boardService.save(board).get();
            return new ResponseEntity<Object>(savedBoard, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/boards/deletion/{idx}")
    public ResponseEntity<HttpStatus> deleteBoard(
            @PathVariable("idx") Long idx
    ) {
        try {
            boardService.deleteById(idx);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

}











