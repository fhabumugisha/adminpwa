package com.buseni.adminpwa.questions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	private QuestionService questionService;
	 public QuestionController(QuestionService messageService) {
		this.questionService = messageService;
	}
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("questions", questionService.findAll());
		return "questions";
	}
	
	
	
	
	

}
