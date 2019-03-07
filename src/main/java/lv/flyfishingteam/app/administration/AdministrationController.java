package lv.flyfishingteam.app.administration;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.participant.Participant;
import lv.flyfishingteam.app.participant.ParticipantService;

@Controller
public class AdministrationController {

	private final ParticipantService participantService;

	AdministrationController(ParticipantService participantService) {
		this.participantService = participantService;
	}

	@GetMapping("/administration")
	public String homePage(Model model) {
		model.addAttribute("participants", participantService.findAll());
		return "views/administration/dashboard";
	}

	@GetMapping("/administration/participants/new")
	public String addParticipant(Model model) {
		model.addAttribute("participantForm", new Participant());
		return "views/administration/newParticipant";
	}

	@PostMapping("/administration/participants")
	public String newParticipant(@ModelAttribute("participantForm") Participant participantForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Participant newParticipant = participantService.save(participantForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/newParticipant";
		}

		String messageText = MessageFormat.format("New participant {0} {1} successfully created!",
				newParticipant.getFirstName(), newParticipant.getLastName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

}
