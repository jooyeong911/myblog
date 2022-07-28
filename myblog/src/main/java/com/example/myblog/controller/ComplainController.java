package com.example.myblog.controller;

import com.example.myblog.model.Complain;
import com.example.myblog.paging.Criteria;
import com.example.myblog.service.ComplainServiceImpl;
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
public class ComplainController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ComplainServiceImpl complainService; // 객체정의(null => 스프링객체)

    //    모든 회원 조회 메뉴
    @GetMapping("/complains")
    public ResponseEntity<Map<String, Object>>
    getAllTitlePage(Criteria criteria) {
        List<Complain> complains = complainService.findByTitleContaining(criteria);

        try {
            if (complains.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("complains", complains);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/complains")
    public ResponseEntity<Object>
    createComplain(@RequestBody Complain complain) {

        Complain savedComplain = complainService.save(complain).get();
        try {
            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/complains/{id}")
    public ResponseEntity<Object>
    getComplainById(@PathVariable("id") Long id) {
        Complain complain = complainService.findById(id).get();

        try {
            if (complain != null) {
                return new ResponseEntity<Object>(complain, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/complains/{id}")
    public ResponseEntity<Object> updateComplain(
            @PathVariable("id") Long id,
            @RequestBody Complain complain
    ) {
        try {
            complain.setId(id);
            Complain savedComplain = complainService.save(complain).get();
            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/complains/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteComplain(
            @PathVariable("id") Long id
    ) {
        try {
            complainService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}










