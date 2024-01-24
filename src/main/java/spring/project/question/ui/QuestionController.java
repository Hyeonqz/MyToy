package spring.project.question.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.project.question.application.QuestionService;
import spring.project.question.application.dto.request.QuestionRequestDto;
import spring.project.question.domain.Question;
import spring.project.question.repository.QuestionRepository;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class QuestionController {
	private final QuestionRepository questionRepository;
	private final QuestionService questionService;

		//Model에 담아서 프론트를 구성해야 할 때
		/*@GetMapping("/question_list")
		public String list(Model model) {
			List<Question> questionList = this.questionRepository.findAll();
			model.addAttribute("questionList",questionList);
			return "board/question_list";
		}*/

	@GetMapping("/question_list")
	public List<QuestionRequestDto> list() {
		return questionService.getListDto();
	}

}
