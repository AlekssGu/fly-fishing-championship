package lv.flyfishingteam.app.administration.championship;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.championship.Championship;
import lv.flyfishingteam.app.championship.ChampionshipService;

@Controller
public class ChampionshipController {

	private final ChampionshipService championshipService;

	ChampionshipController(ChampionshipService championshipService) {
		this.championshipService = championshipService;
	}

	@GetMapping("/administration/championship/new")
	public String addChampionship(Model model) {
		model.addAttribute("championships", championshipService.findAll());
		model.addAttribute("championshipForm", new Championship());
		return "views/administration/championship/new";
	}

	@PostMapping("/administration/championship")
	public String newChampionship(@ModelAttribute("championshipForm") Championship championshipForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Championship newChampionship = championshipService.save(championshipForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/championship/new";
		}

		redirectAttributes.addFlashAttribute("message", "New championship successfully created!");

		return "redirect:/administration";
	}

	@GetMapping("/administration/championship/edit/{championshipId}")
	public String editChampionship(@PathVariable("championshipId") Long championshipId, Model model) {
		Championship championship = championshipService.findById(championshipId).orElseThrow(
				() -> new ChampionshipNotFoundException("Championship not found with id " + championshipId));

		model.addAttribute("championshipForm", championship);

		return "views/administration/championship/edit";
	}

	@PostMapping("/administration/championship/update")
	public String editChampionship(@ModelAttribute("championshipForm") Championship championshipForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Championship editChampionship = championshipService.save(championshipForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/championship/edit";
		}

		redirectAttributes.addFlashAttribute("message", "Championship successfully updated!");

		return "redirect:/administration";
	}

	@GetMapping("/administration/championship/delete/{championshipId}")
	public String deleteChampionship(@PathVariable("championshipId") Long championshipId, RedirectAttributes redirectAttributes) {

		championshipService.delete(championshipId);

		redirectAttributes.addFlashAttribute("message", "Championship deleted!");

		return "redirect:/administration";
	}
}
