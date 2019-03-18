package lv.flyfishingteam.app.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.security.SecurityService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "views/auth/registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "views/auth/registration";
		}

		User newUser = userService.save(userForm);
		securityService.autoLogin(newUser.getUsername(), newUser.getPasswordConfirm());

		redirectAttributes.addFlashAttribute("message", "You have successfully registered and logged in!");
		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(String error, String logout, RedirectAttributes redirectAttributes) {
		if (error != null) {
			redirectAttributes.addFlashAttribute("error", "Your username or password is invalid");
			return "redirect:/login";
		}
		if (logout != null) {
			redirectAttributes.addFlashAttribute("message", "You have been logged out successfully");
			return "redirect:/login";
		}

		return "views/auth/login";
	}
}