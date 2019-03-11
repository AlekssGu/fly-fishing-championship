package lv.flyfishingteam.app.administration.participant;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.participant.Participant;
import lv.flyfishingteam.app.participant.ParticipantService;
import lv.flyfishingteam.app.team.TeamService;

@Controller
public class ParticipantController {

	private final ParticipantService participantService;
	private final TeamService teamService;

	ParticipantController(ParticipantService participantService, TeamService teamService) {
		this.participantService = participantService;
		this.teamService = teamService;
	}

	@GetMapping("/administration/participant/new")
	public String addParticipant(Model model) {
		model.addAttribute("teams", teamService.findAll());
		model.addAttribute("participantForm", new Participant());
		return "views/administration/participant/new";
	}

	@PostMapping("/administration/participant")
	public String newParticipant(@ModelAttribute("participantForm") Participant participantForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Participant newParticipant = participantService.save(participantForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/participant/new";
		}

		String messageText = MessageFormat.format("New participant {0} {1} successfully created!",
				newParticipant.getFirstName(), newParticipant.getLastName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/participant/edit/{participantId}")
	public String editParticipant(@PathVariable("participantId") Long participantId, Model model) {
		Participant participant = participantService.findById(participantId).orElseThrow(
				() -> new ParticipantNotFoundException("Participant not found with id " + participantId));

		model.addAttribute("teams", teamService.findAll());
		model.addAttribute("participantForm", participant);

		return "views/administration/participant/edit";
	}

	@PostMapping("/administration/participant/update")
	public String editParticipant(@ModelAttribute("participantForm") Participant participantForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Participant editParticipant = participantService.save(participantForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/participant/edit";
		}

		String messageText = MessageFormat.format("Participant {0} {1} successfully updated!",
				editParticipant.getFirstName(), editParticipant.getLastName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/participant/delete/{participantId}")
	public String deleteParticipant(@PathVariable("participantId") Long participantId, RedirectAttributes redirectAttributes) {

		participantService.delete(participantId);

		redirectAttributes.addFlashAttribute("message", "Participant deleted!");

		return "redirect:/administration";
	}
}
