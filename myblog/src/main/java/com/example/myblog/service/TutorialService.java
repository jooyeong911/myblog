package com.example.myblog.service;

import com.example.myblog.model.Tutorial;
import com.example.myblog.paging.Criteria;

import java.util.List;
import java.util.Optional;

public interface TutorialService {

	public Optional<Tutorial> findById(long id);
	public List<Tutorial> findByPublished(String published);

	public List<Tutorial> findByTitleContaining(Criteria criteria);

	public List<Tutorial> findAll(Criteria criteria);

	public boolean save(Tutorial tutorial);

	public boolean deleteById(Long id);

	public boolean deleteAll();

}
