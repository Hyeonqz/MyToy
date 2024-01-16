package spring.project.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import spring.project.domain.site.SiteUserForm;
import spring.project.web.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SiteUserController {
	private final UserService userService;

	@GetMapping("/signup")
	public String signup(SiteUserForm siteUserForm) {
		return "signup/signup_form";
	}

	@PostMapping("/signup")
	public String singup(@Valid SiteUserForm siteUserForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup/signup_form";
		}
		if(!siteUserForm.getPassword1().equals(siteUserForm.getPassword2())) {
			// bindingResult.rejectValue(필드명, 오류코드, 에러메시지)
			bindingResult.rejectValue("password2","passwordIncorrect", "2개의 비밀번호가 일치하지 않습니다.");
			return "signup/signup_form";
		}

		//feat : 아래코드는 중복된 ID로 회원가입 시 에외처리
		try {
			userService.create(
				siteUserForm.getUsername(),
				siteUserForm.getPassword1(),
				siteUserForm.getEmail(),
				siteUserForm.getAddr(),
				siteUserForm.getGaipday());

		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed","이미 등록된 사용자 입니다."); //일반적 오류일 때 사용
			return "signup/signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed",e.getMessage());
			return "signup/signup_form";
		}
		// 예외처리 끝

		return "redirect:/question/list";
	}

	@GetMapping("/login")
	public String login() {
		return "login/login_form";
	}

	@GetMapping("/login2")
	public String login2() {
		return "login/login";
	}

}
