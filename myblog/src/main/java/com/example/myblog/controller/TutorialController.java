package com.example.myblog.controller;

import com.example.myblog.model.Tutorial;
import com.example.myblog.paging.Criteria;
import com.example.myblog.service.TutorialServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialServiceImpl tutorialService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

//	Todo : 아래 getTitleTutorials 수정 => getAllTitlePage
	@GetMapping("/tutorials")
	public ResponseEntity<Map<String, Object>>
			getAllTitlePage(Criteria criteria) {
		List<Tutorial> tutorials = tutorialService.findByTitleContaining(criteria);

		try {
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("tutorials", tutorials);
			response.put("currentPage", criteria.getPage());
			response.put("totalItems", criteria.getTotalItems());
			response.put("totalPages", criteria.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorial = tutorialService.findById(id);

		if (tutorial.isPresent()) {
			return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		logger.info("tutorial {}", tutorial);
		boolean bSuccess = tutorialService.save(tutorial);
				logger.info("bSuccess {}", bSuccess);
		try {
			if(bSuccess == true) {
				return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialService.findById(id);

		if (tutorialData.isPresent()) {
			boolean bSuccess = tutorialService.save(tutorial);

			if(bSuccess == true) {
				return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
			}

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/tutorials/deletion/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {

		boolean bSuccess = tutorialService.deleteById(id);

		logger.info("bSuccess {}", bSuccess);

		try {

			if(bSuccess == true) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		boolean bSuccess = tutorialService.deleteAll();
		logger.info("bSuccess {}", bSuccess);

		try {

			if(bSuccess == true) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {

			List<Tutorial> tutorials = tutorialService.findByPublished("Y");

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
