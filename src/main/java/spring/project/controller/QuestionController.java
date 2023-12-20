package spring.project.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import spring.project.dto.AnswerForm;
import spring.project.dto.Question;
import spring.project.dto.QuestionForm;
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
	public String list(Model model,
						@RequestParam(value="page",defaultValue = "0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging",paging);
		return "question_list";
	}

	@GetMapping(value="/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question",question);
		return "question_detail";
	}

	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	//아래위 같아도 상관없는 이유 => 메소드 오버로딩
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		/*
		* BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
		* 만약 2개의 매개변수의 위치가 정확하지 않다면 @Valid만 적용이 되어 입력값 검증 실패 시 400 오류가 발생한다.
		* */

		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
}
