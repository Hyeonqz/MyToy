package spring.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import spring.project.dto.Question;
import spring.project.repository.QuestionRepository;
import spring.project.service.QuestionService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

	//Requiredargsconstructor 는 questionRepository 속성을 포함하는 생성자를 생성한다.
	//final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다.
	//이 어노테이션 때문에 questionRepository가 bean이 주입이 된다.

	private final QuestionService questionService;

	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
	//redirect:<URL> - URL로 리다이렉트 (리다이렉트는 완전히 새로운 URL로 요청이 된다.)
	//forward:<URL> - URL로 포워드 (포워드는 기존 요청 값들이 유지된 상태로 URL이 전환된다.)

	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList",questionList);
		return "question_list";
	}

	@GetMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "question_detail";
	}
}
