package lv.flyfishingteam.app.administration.stage;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.stage.Stage;
import lv.flyfishingteam.app.stage.StageService;

@Controller
public class StageController {

	private final StageService stageService;

	StageController(StageService stageService) {
		this.stageService = stageService;
	}

	@GetMapping("/administration/stage/new")
	public String addStage(Model model) {
		model.addAttribute("stageForm", new Stage());
		return "views/administration/stage/new";
	}

	@PostMapping("/administration/stage")
	public String newStage(@ModelAttribute("stageForm") Stage stageForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Stage stage = stageService.save(stageForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/stage/new";
		}

		String messageText = MessageFormat.format("New stage {0} successfully created!", stage.getName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/stage/edit/{stageId}")
	public String editStage(@PathVariable("stageId") Long stageId, Model model) {
		Stage stage = stageService.findById(stageId).orElseThrow(
				() -> new StageNotFoundException("Stage not found with id " + stageId));

		model.addAttribute("stageForm", stage);

		return "views/administration/stage/edit";
	}

	@PostMapping("/administration/stage/update")
	public String editStage(@ModelAttribute("stageForm") Stage stageForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Stage stageTeam = stageService.save(stageForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/stage/edit";
		}

		String messageText = MessageFormat.format("Stage {0} successfully updated!", stageTeam.getName());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration";
	}

	@GetMapping("/administration/stage/delete/{stageId}")
	public String deleteStage(@PathVariable("stageId") Long stageId, RedirectAttributes redirectAttributes) {

		stageService.delete(stageId);

		redirectAttributes.addFlashAttribute("message", "Stage deleted!");

		return "redirect:/administration";
	}

}
