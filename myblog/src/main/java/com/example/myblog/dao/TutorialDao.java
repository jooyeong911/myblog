package com.example.myblog.dao;

import com.example.myblog.model.Tutorial;
import com.example.myblog.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TutorialDao {

	public List<Tutorial> findByPublished(String published);

	public List<Tutorial> findByTitleContaining(Criteria criteria);
	public List<Tutorial> findAll(Criteria criteria);

	int selectTotalCount(String title);

	public Optional<Tutorial> findById(Long id);

	public int insertTutorial(Tutorial tutorial);

	public int updateTutorial(Tutorial tutorial);

	public int deleteById(Long id);

	public int deleteAll();

}