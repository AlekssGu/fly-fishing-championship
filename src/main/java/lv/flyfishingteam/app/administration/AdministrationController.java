package lv.flyfishingteam.app.administration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.flyfishingteam.app.participant.ParticipantService;
import lv.flyfishingteam.app.team.TeamService;

@Controller
public class AdministrationController {

	private final ParticipantService participantService;
	private final TeamService teamService;

	AdministrationController(ParticipantService participantService, TeamService teamService) {
		this.participantService = participantService;
		this.teamService = teamService;
	}

	@GetMapping("/administration")
	public String homePage(Model model) {
		model.addAttribute("participants", participantService.findAll());
		model.addAttribute("teams", teamService.findAll());
		return "views/administration/dashboard";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "views/error/accessDenied";
	}

}
