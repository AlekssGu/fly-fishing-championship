package lv.flyfishingteam.app.stage.session;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class StageSessionServiceImpl implements StageSessionService {

	private final StageSessionRepository stageSessionRepository;

	StageSessionServiceImpl(StageSessionRepository stageSessionRepository) {
		this.stageSessionRepository = stageSessionRepository;
	}

	@Override
	public StageSession save(@Valid StageSession stageSession) {
		return stageSessionRepository.save(stageSession);
	}

	@Override
	public List<StageSession> findAll() {
		return stageSessionRepository.findAll();
	}

	@Override
	public Optional<StageSession> findById(Long id) {
		return stageSessionRepository.findById(id);
	}

	@Override
	public void delete(Long stageSessionId) {
		stageSessionRepository.deleteById(stageSessionId);
	}
}
