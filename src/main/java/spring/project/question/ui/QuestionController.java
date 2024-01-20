package spring.project.question.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import spring.project.question.domain.Question;
import spring.project.question.repository.QuestionRepository;

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
