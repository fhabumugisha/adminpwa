package com.buseni.adminpwa.questions;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepo questionRepo;
	
	public QuestionServiceImpl(QuestionRepo messageRepo) {
		this.questionRepo = messageRepo;
	}

	@Override
	public List<Question> findAll() {
		return questionRepo.findAll();
	}

	@Override
	@Transactional
	public Question save(Question question) {
		question.setDateCreation(new Date());
		return questionRepo.save(question);
	}

}
