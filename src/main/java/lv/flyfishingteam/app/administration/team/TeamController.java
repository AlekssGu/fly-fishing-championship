package lv.flyfishingteam.app.administration.team;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.team.Team;
import lv.flyfishingteam.app.team.TeamService;

@Controller
public class TeamController {

	private final TeamService teamService;

	TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/administration/team/new")
	public String addTeam(Model model) {
		model.addAttribute("teamForm", new Team());
		return "views/administration/team/new";
	}

	@PostMapping("/administration/team")
	public String newTeam(@ModelAttribute("teamForm") Team teamForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Team team = teamService.save(teamForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/team/new";
		}

		String messageText = MessageFormat.format("New team {0} successfully created!", team.getTeamName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/team/edit/{teamId}")
	public String editTeam(@PathVariable("teamId") Long teamId, Model model) {
		Team team = teamService.findById(teamId).orElseThrow(
				() -> new TeamNotFoundException("Team not found with id " + teamId));

		model.addAttribute("teamForm", team);

		return "views/administration/team/edit";
	}

	@PostMapping("/administration/team/update")
	public String editTeam(@ModelAttribute("teamForm") Team teamForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Team editTeam = teamService.save(teamForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/team/edit";
		}

		String messageText = MessageFormat.format("Team {0} successfully updated!", editTeam.getTeamName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/team/delete/{teamId}")
	public String deleteTeam(@PathVariable("teamId") Long teamId, RedirectAttributes redirectAttributes) {

		teamService.delete(teamId);

		redirectAttributes.addFlashAttribute("message", "Team deleted!");

		return "redirect:/administration";
	}
}
