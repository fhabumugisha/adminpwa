package com.buseni.adminpwa.questions;

import java.util.List;

public interface QuestionService {
	
	List<Question>  findAll();
	
	Question save(Question question);
	
	

}
