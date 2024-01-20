package spring.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/")
	public String root() {
		//로그인이 되어있으면 여기로
		return "redirect:/question/list";

		//로그인이 안돼 있을 경우 로그인창으로 넘기기.
	}
}
