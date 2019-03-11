package lv.flyfishingteam.app.participant;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface ParticipantService {

	Participant save(@Valid Participant participant);

	List<Participant> findAll();

	Optional<Participant> findById(Long id);

	void delete(Long participantId);
}
