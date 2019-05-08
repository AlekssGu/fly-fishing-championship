package lv.flyfishingteam.app.administration.championship.stage.session;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.administration.championship.ChampionshipNotFoundException;
import lv.flyfishingteam.app.administration.championship.stage.StageNotFoundException;
import lv.flyfishingteam.app.participant.ParticipantService;
import lv.flyfishingteam.app.stage.Stage;
import lv.flyfishingteam.app.stage.StageService;
import lv.flyfishingteam.app.stage.session.StageSession;
import lv.flyfishingteam.app.stage.session.StageSessionService;
import lv.flyfishingteam.app.stage.session.result.Result;
import lv.flyfishingteam.app.stage.session.result.ResultService;

@Controller
public class SessionController {

	private final StageService stageService;
	private final StageSessionService stageSessionService;
	private final ResultService resultService;
	private final ParticipantService participantService;

	SessionController(StageService stageService, StageSessionService stageSessionService, ResultService resultService, ParticipantService participantService) {
		this.stageService = stageService;
		this.stageSessionService = stageSessionService;
		this.resultService = resultService;
		this.participantService = participantService;
	}

	@GetMapping("/administration/championship/stage/{stageId}/sessions")
	public String allSessions(@PathVariable("stageId") Long stageId, Model model) {
		Stage stage = stageService.findById(stageId).orElseThrow(
				() ->  new StageNotFoundException("Stage not found with id " + stageId));

		model.addAttribute("stage", stage);
		model.addAttribute("sessions", stageSessionService.findByStageId(stage.getId()));

		return "views/administration/championship/stage/session/index";
	}

	@GetMapping("/administration/championship/stage/session/{sessionId}/results")
	public String allSessionResults(@PathVariable("sessionId") Long sessionId, Model model) {
		StageSession stageSession = stageSessionService.findById(sessionId).orElseThrow(
				() ->  new StageSessionNotFoundException("Stage session not found with id " + sessionId));
		List<Result> sessionResults = resultService.findBySessionId(stageSession.getId());

		model.addAttribute("stageSession", stageSession);
		model.addAttribute("results", sessionResults);

		return "views/administration/championship/stage/session/results/index";
	}

	@GetMapping("/administration/championship/stage/session/delete/{sessionId}")
	public String deleteStage(@PathVariable("sessionId") Long sessionId, RedirectAttributes redirectAttributes) {

		StageSession stageSession = stageSessionService.findById(sessionId).orElseThrow(() -> new StageSessionNotFoundException("Stage session not found with id " + sessionId));
		stageSessionService.delete(stageSession.getId());

		redirectAttributes.addFlashAttribute("message", "Stage session deleted!");

		return "redirect:/administration/championship/stage/" + stageSession.getStage().getId() + "/sessions";
	}

	@GetMapping("/administration/championship/stage/session/{sessionId}/result/new")
	public String addStage(@PathVariable("sessionId") Long sessionId, Model model) {
		StageSession stageSession = stageSessionService.findById(sessionId).orElseThrow(
				() ->  new ChampionshipNotFoundException("Stage session not found with id " + sessionId));

		Result result = new Result();
		result.setStageSession(stageSession);

		model.addAttribute("participants", participantService.findAll());
		model.addAttribute("resultForm", result);

		return "views/administration/championship/stage/session/results/new";
	}

	@PostMapping("/administration/championship/stage/session/result")
	public String newStageSessionResult(@ModelAttribute("resultForm") Result resultForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		Result result = resultService.save(resultForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/championship/stage/session/results/new";
		}
		redirectAttributes.addFlashAttribute("message", "New result for participant successfully added!");

		return "redirect:/administration/championship/stage/session/" + result.getStageSession().getId() + "/results";
	}

	@GetMapping("/administration/championship/stage/session/result/delete/{resultId}")
	public String deleteResult(@PathVariable("resultId") Long resultId, RedirectAttributes redirectAttributes) {

		Result result = resultService.findById(resultId).orElseThrow(() -> new ResultNotFoundException("Result not found with id " + resultId));
		resultService.delete(result.getId());

		redirectAttributes.addFlashAttribute("message", "Result deleted!");

		return "redirect:/administration/championship/stage/session/" + result.getStageSession().getId() + "/results";
	}
}
