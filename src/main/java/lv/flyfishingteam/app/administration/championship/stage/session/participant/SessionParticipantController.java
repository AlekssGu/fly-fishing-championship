package lv.flyfishingteam.app.administration.championship.stage.session.participant;

import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.flyfishingteam.app.administration.championship.stage.session.StageSessionNotFoundException;
import lv.flyfishingteam.app.participant.ParticipantService;
import lv.flyfishingteam.app.stage.StageService;
import lv.flyfishingteam.app.stage.session.StageSession;
import lv.flyfishingteam.app.stage.session.StageSessionService;
import lv.flyfishingteam.app.stage.session.participant.SessionParticipant;
import lv.flyfishingteam.app.stage.session.participant.SessionParticipantService;

@Controller
public class SessionParticipantController {

	private final StageService stageService;
	private final StageSessionService stageSessionService;
	private final ParticipantService participantService;
	private final SessionParticipantService sessionParticipantService;

	SessionParticipantController(StageService stageService, StageSessionService stageSessionService,
			ParticipantService participantService, SessionParticipantService sessionParticipantService) {
		this.stageService = stageService;
		this.stageSessionService = stageSessionService;
		this.participantService = participantService;
		this.sessionParticipantService = sessionParticipantService;
	}

	@GetMapping("/administration/stage/{stageId}/session/{sessionId}/participant/new")
	public String addStage(Model model, @PathVariable("stageId") Long stageId,
			@PathVariable("sessionId") Long sessionId) {

		StageSession stageSession = stageSessionService.findById(sessionId).orElseThrow(
				() -> new StageSessionNotFoundException("Stage session not found with id " + sessionId));

		SessionParticipant sessionParticipant = new SessionParticipant();
		sessionParticipant.setStageSession(stageSession);
		model.addAttribute("sessionParticipantForm", sessionParticipant);
		model.addAttribute("participants", participantService.findAll());

		return "views/administration/stage/session/participant/new";
	}

	@PostMapping("/administration/stage/session/participant/new")
	public String addSessionParticipant(
			@ModelAttribute("sessionParticipantForm") SessionParticipant sessionParticipantForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		SessionParticipant sessionParticipant = sessionParticipantService.save(sessionParticipantForm);

		if (bindingResult.hasErrors()) {
			return "views/administration/stage/new";
		}

		String messageText = MessageFormat
				.format("{0} allocated to sector {1}, zone {2}!", sessionParticipant.getParticipant().getFullName(),
						sessionParticipant.getSector(), sessionParticipant.getZone());
		redirectAttributes.addFlashAttribute("message", messageText);

		return "redirect:/administration/stage/" + sessionParticipant.getStageSession().getStage().getId() + "/session/"
				+ sessionParticipant.getStageSession().getId() + "/participant/new";
	}

}
