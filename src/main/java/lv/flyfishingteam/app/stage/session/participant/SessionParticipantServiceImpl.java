package lv.flyfishingteam.app.stage.session.participant;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class SessionParticipantServiceImpl implements SessionParticipantService {

	private final SessionParticipantRepository sessionParticipantRepository;

	SessionParticipantServiceImpl(SessionParticipantRepository sessionParticipantRepository) {
		this.sessionParticipantRepository = sessionParticipantRepository;
	}

	@Override
	public SessionParticipant save(@Valid SessionParticipant sessionParticipant) {
		return sessionParticipantRepository.save(sessionParticipant);
	}

	@Override
	public List<SessionParticipant> findAll() {
		return sessionParticipantRepository.findAll();
	}

	@Override
	public Optional<SessionParticipant> findById(Long id) {
		return sessionParticipantRepository.findById(id);
	}

	@Override
	public void delete(Long sessionParticipantId) {
		sessionParticipantRepository.deleteById(sessionParticipantId);
	}
}
