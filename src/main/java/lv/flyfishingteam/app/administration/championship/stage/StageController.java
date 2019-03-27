package lv.flyfishingteam.app.administration.championship.stage;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.administration.championship.ChampionshipNotFoundException;
import lv.flyfishingteam.app.championship.Championship;
import lv.flyfishingteam.app.championship.ChampionshipService;
import lv.flyfishingteam.app.stage.Stage;
import lv.flyfishingteam.app.stage.StageService;
import lv.flyfishingteam.app.stage.session.StageSession;
import lv.flyfishingteam.app.stage.session.StageSessionService;

@Controller
public class StageController {

	private static final int DEFAULT_STAGE_COUNT = 6;

	private final StageService stageService;
	private final ChampionshipService championshipService;
	private final StageSessionService stageSessionService;

	StageController(StageService stageService, ChampionshipService championshipService, StageSessionService stageSessionService) {
		this.stageService = stageService;
		this.championshipService = championshipService;
		this.stageSessionService = stageSessionService;
	}

	@GetMapping("/administration/championship/{championshipId}/stages")
	public String allStages(@PathVariable("championshipId") Long championshipId, Model model) {
		Championship championship = championshipService.findById(championshipId).orElseThrow(
				() ->  new ChampionshipNotFoundException("Championship not found with id " + championshipId));

		model.addAttribute("championship", championship);
		model.addAttribute("stages", stageService.findByChampionshipId(championshipId));

		return "views/administration/championship/stage/index";
	}

	@GetMapping("/administration/championship/{championshipId}/stage/new")
	public String addStage(@PathVariable("championshipId") Long championshipId, Model model) {
		Championship championship = championshipService.findById(championshipId).orElseThrow(
				() ->  new ChampionshipNotFoundException("Championship not found with id " + championshipId));

		Stage stage = new Stage();
		stage.setChampionship(championship);
		model.addAttribute("stageForm", stage);

		return "views/administration/championship/stage/new";
	}

	@PostMapping("/administration/championship/stage")
	public String newStage(@ModelAttribute("stageForm") Stage stageForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Stage stage = stageService.save(stageForm);

		createStageSessions(stage);

		if (bindingResult.hasErrors()) {
			return "views/administration/championship/stage/new";
		}

		String messageText = MessageFormat.format("New stage {0} successfully created!", stage.getName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration/championship/" + stage.getChampionship().getId() + "/stages";
	}

	@GetMapping("/administration/championship/stage/edit/{stageId}")
	public String editStage(@PathVariable("stageId") Long stageId, Model model) {
		Stage stage = stageService.findById(stageId).orElseThrow(
				() -> new StageNotFoundException("Stage not found with id " + stageId));

		model.addAttribute("stageForm", stage);
		model.addAttribute("championships", championshipService.findAll());

		return "views/administration/championship/stage/edit";
	}

	@PostMapping("/administration/championship/stage/update")
	public String editStage(@ModelAttribute("stageForm") Stage stageForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Stage stage = stageService.save(stageForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/championship/stage/edit";
		}

		String messageText = MessageFormat.format("Stage {0} successfully updated!", stage.getName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration/championship/" + stage.getChampionship().getId() + "/stages";
	}

	@GetMapping("/administration/championship/stage/delete/{stageId}")
	public String deleteStage(@PathVariable("stageId") Long stageId, RedirectAttributes redirectAttributes) {

		Stage stage = stageService.findById(stageId).orElseThrow(() -> new StageNotFoundException("Stage not found with id " + stageId));
		stageService.delete(stage.getId());

		redirectAttributes.addFlashAttribute("message", "Stage deleted!");

		return "redirect:/administration/championship/" + stage.getChampionship().getId() + "/stages";
	}

	private void createStageSessions(Stage stage) {
		for (int i = 0; i < DEFAULT_STAGE_COUNT; i++) {
			StageSession stageSession = new StageSession();
			stageSession.setSequence(i + 1);
			stageSession.setStage(stage);
			stageSessionService.save(stageSession);
		}
	}

}
