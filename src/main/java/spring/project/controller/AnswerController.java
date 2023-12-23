package spring.project.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import spring.project.dto.AnswerForm;
import spring.project.dto.Question;
import spring.project.dto.SiteUser;
import spring.project.repository.AnswerRepository;
import spring.project.service.AnswerService;
import spring.project.service.QuestionService;
import spring.project.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
		@Valid AnswerForm answerForm,
		BindingResult bindingResult,
		Principal principal) {
		//principal.getName()을 호출시 현재 로그인한 사용자의 사용자명(ID)를 알 수 있다.

		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());

		if(bindingResult.hasErrors()) {
			model.addAttribute("question",question);
			return "question_detatil";
		}
		this.answerService.create(question,answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s",id);
	}

}
