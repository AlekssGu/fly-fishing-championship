package lv.flyfishingteam.app.participant;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private final ParticipantRepository participantRepository;

	ParticipantServiceImpl(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}

	@Override
	public Participant save(@Valid Participant participant) {
			return participantRepository.save(participant);
	}

	@Override
	public List<Participant> findAll() {
		return participantRepository.findAll();
	}
}
