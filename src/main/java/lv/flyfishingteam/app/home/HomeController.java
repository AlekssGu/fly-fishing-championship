package lv.flyfishingteam.app.home;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Value("${spring.application.name}")
	String appName;

	@GetMapping({"/", "/home"})
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "views/home";
	}

	@GetMapping("/teams")
	public String teamsPage(Model model) {
		model.addAttribute("appName", appName);
		return "views/teams";
	}

	@GetMapping("/rules")
	public String rulesPage(Model model) {
		model.addAttribute("appName", appName);
		return "views/rules";
	}
}
