package spring.project.admin.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dash")
public class DashController {

	@GetMapping("/board")
	public String board() {
		return "dash/dashboard";
	}
}
