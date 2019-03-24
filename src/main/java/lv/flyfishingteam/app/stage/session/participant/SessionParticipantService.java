package lv.flyfishingteam.app.stage.session.participant;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface SessionParticipantService {

	SessionParticipant save(@Valid SessionParticipant sessionParticipant);

	List<SessionParticipant> findAll();

	Optional<SessionParticipant> findById(Long id);

	void delete(Long sessionParticipantId);

}
