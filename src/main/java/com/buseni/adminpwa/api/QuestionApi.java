package com.buseni.adminpwa.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buseni.adminpwa.questions.Question;
import com.buseni.adminpwa.questions.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="Questions api")
@RequestMapping("/api/v1/questions")
public class QuestionApi {
	
public static final Logger LOGGER = LoggerFactory.getLogger( QuestionApi.class );
	
	
	private QuestionService questionService;
	 public QuestionApi(QuestionService questionService) {
		this.questionService = questionService;
	}
	 
	 @PostMapping("")
	 @ApiOperation(value="Save question")
	 public ResponseEntity<Object>   save(@RequestBody Question question){
		 Question saved =  questionService.save(question);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(saved.getId()).toUri();
			return ResponseEntity.created(location).build();
		
	 }

}
