package spring.project.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import spring.project.domain.question.Question;
import spring.project.domain.question.QuestionRepository;
import spring.project.web.dto.QuestionDto;
import spring.project.web.service.QuestionService;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	private final QuestionRepository questionRepository;

	@GetMapping("/question/list")
	public String list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList",questionList);
		return "board/question_list";
	}

}
