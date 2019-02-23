package lv.flyfishingteam.app.administration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationController {

	@GetMapping("/administration")
	public String homePage() {
		return "views/administration/dashboard";
	}

}
