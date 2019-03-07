package lv.flyfishingteam.app.participant;

import java.util.List;

import javax.validation.Valid;

public interface ParticipantService {

	Participant save(@Valid Participant participant);

	List<Participant> findAll();

}
