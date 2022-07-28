package com.example.myblog.service;

import com.example.myblog.dao.TutorialDao;
import com.example.myblog.model.Tutorial;
import com.example.myblog.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private TutorialDao tutorialDao;

	Logger logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public Optional<Tutorial> findById(long id) {
		return tutorialDao.findById(id);
	}

	@Override
	public List<Tutorial> findByPublished(String published) {
		return tutorialDao.findByPublished(published);
	}

//	Todo : findByTitleContaining, findAll 수정
	@Override
	public List<Tutorial> findByTitleContaining(Criteria criteria) {
		List<Tutorial> tutorials = Collections.emptyList();

		Optional<String> optionalCriteria
				= Optional.ofNullable(criteria.getTitle());

		int totalCount =
				tutorialDao.selectTotalCount(optionalCriteria.orElse(""));

		criteria.setTotalItems(totalCount);
		criteria.setTotalPages(totalCount / criteria.getSize());

		if (criteria.getTitle() == null)
			tutorials = tutorialDao.findAll(criteria);
		else
			tutorials = tutorialDao.findByTitleContaining(criteria);

		return tutorials;
	}

	@Override
	public List<Tutorial> findAll(Criteria criteria) {

		return tutorialDao.findAll(criteria);
	}

	@Override
	public boolean save(Tutorial tutorial) {
		int queryResult = 0;

		logger.info("tutorial {}", tutorial);

		if (tutorial.getId() == null) {
			queryResult = tutorialDao.insertTutorial(tutorial);
		} else {
			queryResult = tutorialDao.updateTutorial(tutorial);
		}

		return (queryResult >= 1) ? true : false;
	}

	@Override
	public boolean deleteById(Long id) {
		int queryResult = 0;

		queryResult = tutorialDao.deleteById(id);

		return (queryResult >= 1) ? true : false;
	}

	@Override
	public boolean deleteAll() {
		int queryResult = 0;

		queryResult = tutorialDao.deleteAll();

		return (queryResult >= 1) ? true : false;
	}
}
