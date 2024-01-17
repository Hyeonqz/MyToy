package spring.project.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import spring.project.web.dto.QuestionDto;
import spring.project.web.service.QuestionService;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	private final QuestionService questionService;

	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody QuestionDto questionDto) {
		return questionService.save(questionDto);
	}
}
